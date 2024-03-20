package tech.mobile.social.presentation.auth.login2

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.mobile.social.Screens

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class Login2Coordinator(
    val viewModel: Login2ViewModel,
    val navController: NavController
) {
    val screenStateFlow = viewModel.stateFlow

    fun doLogin() {
        // call api login
        Log.d("Login", "doooooooooo")
        viewModel.doLogin()
        navController.navigate(Screens.Home.route)
    }

    fun navRegister() {
        navController.navigate(Screens.Register.route)
    }
}

@Composable
fun rememberLogin2Coordinator(
    viewModel: Login2ViewModel = hiltViewModel(),
    navController: NavController
): Login2Coordinator {
    return remember(viewModel) {
        Login2Coordinator(
            viewModel = viewModel,
            navController = navController
        )
    }
}