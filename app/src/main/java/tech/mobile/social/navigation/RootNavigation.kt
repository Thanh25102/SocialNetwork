package tech.mobile.social.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import tech.mobile.social.Screens
import tech.mobile.social.navigation.app.BottomNavigationBar
import tech.mobile.social.presentation.auth.login.LoginRoute
import tech.mobile.social.presentation.auth.register.RegisterRoute


@Composable
fun RootNavigation() {
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
                    LoginRoute(navController)
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