package tech.mobile.social.presentation.auth.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import kotlinx.coroutines.flow.combine
import tech.mobile.social.shared.UserState


data class CombinedUiState(
    val loginState: LoginState,
    val userState: UserState
)

@Composable
fun LoginRoute(
    navController: NavController,
    onLoginGoogle: () -> Unit,
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

    // UI Rendering
    if (email != null) {
        LoginScreen(uiState, actions, onLoginGoogle, email)
    } else {
        LoginScreen(uiState, actions, onLoginGoogle)
    }


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
        )
    }
}