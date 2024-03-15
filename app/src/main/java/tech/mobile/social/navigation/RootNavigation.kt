package tech.mobile.social.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import tech.mobile.social.Screens
import tech.mobile.social.navigation.app.BottomNavigationBar
import tech.mobile.social.presentation.auth.ForgotPasswordScreen
import tech.mobile.social.presentation.auth.LoginScreen
import tech.mobile.social.presentation.auth.RegisterScreen
import tech.mobile.social.ui.theme.NavigationBarTheme

@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Auth.route) {
        //auth navigation
        navigation(
            startDestination = Screens.Login.route, route = Screens.Auth.route
        ) {
            composable(route = Screens.Login.route) {
                NavigationBarTheme {
                    LoginScreen(navController)
                }
            }
            composable(route = Screens.Register.route) {
                NavigationBarTheme {
                    RegisterScreen(navController)
                }
            }
            composable(route = Screens.ForgotPassword.route) {
                NavigationBarTheme {
                    ForgotPasswordScreen(navController)
                }
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