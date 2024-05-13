package tech.mobile.social.presentation.auth.login

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import tech.mobile.social.shared.UserState


data class CombinedUiState(
    val loginState: LoginState,
    val userState: UserState
)

@Composable
fun LoginRoute(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    googleAuthUiClient: GoogleAuthUiClient,
    coordinator: LoginCoordinator = rememberLoginCoordinator(navController = navController)
) {


    // State observing and declarations
    val loginStateFlow = coordinator.loginStateFlow
    val userStateFlow = coordinator.userStateFlow


    val combinedUiStateFlow = combine(loginStateFlow, userStateFlow) { loginState, userState ->
        CombinedUiState(loginState, userState)
    }

    val uiState by combinedUiStateFlow.collectAsState(
        initial = CombinedUiState(
            LoginState(),
            UserState()
        )
    )


    // UI Actions
    val actions = rememberLoginActions(coordinator)

    val email =
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("email")?.value

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                lifecycleScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )

                    /**
                     * Thực hiện đăng ký tài khoản
                     * Nếu có lỗi thì thực hiện đăng nhập
                     * Nếu có lỗi thì thông báo lỗi
                     */

                    if (signInResult.data != null) {
                        actions.loading()
                        actions.onLoginGoogle(
                            signInResult.data?.fullName!!,
                            signInResult.data?.email!!
                        )
                    }
                }
            }
        }
    )

    /**
     * Kiểm tra xem người dùng đã đăng nhập bằng gg chưa
     */
    LaunchedEffect(key1 = Unit) {
        if (googleAuthUiClient.getSignedInUser() != null) {
            googleAuthUiClient.signOut()
        }
    }


    // UI Rendering
    LoginScreen(uiState, actions, email = email ?: "", onLoginGoogle = {
        lifecycleScope.launch {
            val signInIntentSender = googleAuthUiClient.signIn()
            launcher.launch(
                IntentSenderRequest.Builder(
                    signInIntentSender ?: return@launch
                ).build()
            )
        }
    })


}


@Composable
fun rememberLoginActions(coordinator: LoginCoordinator): LoginActions {
    return remember(coordinator) {
        LoginActions(
            onLogin = coordinator::doLogin,
            onLoginGoogle = coordinator::doLoginGoogle,
            onPasswordChange = coordinator.loginViewModel::updatePassword,
            onEmailChange = coordinator.loginViewModel::updateEmail,
            navRegister = coordinator::navRegister,
            navApp = coordinator::navApp,
            navForgotPassword = coordinator::navForgotPassword,
            loading = coordinator::loading,
            checkLogin = coordinator::checkLogin,
        )
    }
}