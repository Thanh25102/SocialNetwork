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
import tech.mobile.social.presentation.auth.forgot.ForgotRoute
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import tech.mobile.social.presentation.auth.otp.OtpRoute

@Composable
fun RootNavigation(
    lifecycleScope: LifecycleCoroutineScope,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val navController = rememberNavController()



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
                    LoginRoute(navController, lifecycleScope, googleAuthUiClient)
                }
                composable(route = Screens.Register.route) {
                    RegisterRoute(navController = navController)
                }
                composable(route = Screens.ForgotPassword.route) {
                    ForgotRoute(navController)
                }
                composable(
                    route = Screens.Otp.route, arguments = listOf(
                        navArgument(name = "email") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    val email = it.arguments?.getString("email")
                    requireNotNull(email)
                    OtpRoute(email, navController)
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