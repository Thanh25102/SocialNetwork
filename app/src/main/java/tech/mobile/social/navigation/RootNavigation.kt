package tech.mobile.social.navigation


import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import tech.mobile.social.Screens
import tech.mobile.social.navigation.app.BottomNavigationBar
import tech.mobile.social.presentation.auth.login.GoogleAuthUiClient
import tech.mobile.social.presentation.auth.login.LoginRoute
import tech.mobile.social.presentation.auth.register.RegisterRoute
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@Composable
fun RootNavigation(
    lifecycleScope: LifecycleCoroutineScope,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val navController = rememberNavController()
    /**
     * Kiểm tra xem người dùng đã đăng nhập bằng gg chưa
     */
    LaunchedEffect(key1 = Unit) {
        if (googleAuthUiClient.getSignedInUser() != null) {
//            navController.navigate("profile")
            Log.e("Login Google", "Success")
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                lifecycleScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    Log.e("Result login google", signInResult.toString())
                }
            }
        }
    )

    Scaffold() { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Auth.route,
            modifier = Modifier.padding(paddingValues.calculateBottomPadding())
        ) {
            //auth navigation
            navigation(
                startDestination = Screens.Login.route, route = Screens.Auth.route
            ) {
                composable(route = Screens.Login.route) {
                    LoginRoute(navController, onLoginGoogle = {
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
                composable(route = Screens.Register.route) {
                    RegisterRoute(navController = navController)
                }
                composable(route = Screens.ForgotPassword.route) {

                }
            }

            //app navigation
            navigation(
                startDestination = Screens.Home.route, route = Screens.AppRoot.route
            ) {
                composable(Screens.Home.route) {
                    BottomNavigationBar(rootNavController = navController)
                }
            }
        }
    }

}