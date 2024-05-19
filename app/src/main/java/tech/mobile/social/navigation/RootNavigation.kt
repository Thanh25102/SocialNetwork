package tech.mobile.social.navigation


import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import tech.mobile.social.presentation.auth.otp.OtpRoute
import tech.mobile.social.shared.UserState
import tech.mobile.social.shared.UserViewModel


@Composable
fun RootNavigation(
    lifecycleScope: LifecycleCoroutineScope,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val navController = rememberNavController()

    var isLogin by remember {
        mutableStateOf(false)
    }



    Scaffold() { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = if (isLogin) Screens.Home.route else Screens.Login.route,
            modifier = Modifier.padding(paddingValues.calculateBottomPadding())
        ) {

            composable(route = Screens.Login.route) {
                LoginRoute(navController, lifecycleScope, googleAuthUiClient, onLogin = {
                    isLogin = true
                }, onLogout = {
                    isLogin = false
                })
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

            composable(Screens.Home.route) {
                BottomNavigationBar(onLogout = {
                    isLogin = false
                })
            }


        }
    }

}