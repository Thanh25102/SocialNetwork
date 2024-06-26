package tech.mobile.social.presentation.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.mobile.social.Screens
import tech.mobile.social.shared.UserViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
import com.google.android.gms.auth.api.identity.Identity

class LoginCoordinator(
    val loginViewModel: LoginViewModel,
    val userViewModel: UserViewModel,
    val navController: NavController
) {
    val loginStateFlow = loginViewModel.stateFlow
    val userStateFlow = userViewModel.stateFlow
    fun doLogin() {
        userViewModel.doLogin(loginStateFlow.value.email, loginStateFlow.value.password)
    }


    fun doLoginGoogle(fullname: String, email: String) {
        userViewModel.doLoginGoogle(fullname, email)
    }

    fun loading() {
        userViewModel.loading()
    }

    fun navRegister() {
        navController.navigate(Screens.Register.route)
    }

    fun navApp() {
        navController.navigate(Screens.Home.route)
    }

    fun navForgotPassword() {
        navController.navigate(Screens.ForgotPassword.route)
    }

    fun checkLogin() {
        userViewModel.checkLogin()
    }


}

@Composable
fun rememberLoginCoordinator(
    loginViewModel: LoginViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    navController: NavController
): LoginCoordinator {
    return remember(loginViewModel) {
        LoginCoordinator(
            loginViewModel = loginViewModel,
            userViewModel = userViewModel,
            navController = navController
        )
    }
}