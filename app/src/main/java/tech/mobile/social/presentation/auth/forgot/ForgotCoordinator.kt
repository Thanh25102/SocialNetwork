package tech.mobile.social.presentation.auth.forgot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ForgotCoordinator(
    val navController: NavController,
    val viewModel: ForgotViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doUpdateEmail(email: String) {
        viewModel.updateEmail(email)
    }

    fun doForgotPassword() {
        viewModel.forgotPassword()
    }

    fun navResetPassword() {
        val email = screenStateFlow.value.email
        navController.navigate("otp/$email")
        viewModel.resetSucces()
    }
}

@Composable
fun rememberForgotCoordinator(
    navController: NavController,
    viewModel: ForgotViewModel = hiltViewModel()
): ForgotCoordinator {
    return remember(viewModel) {
        ForgotCoordinator(
            navController = navController,
            viewModel = viewModel
        )
    }
}