package tech.mobile.social.presentation.auth.otp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.mobile.social.Screens

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class OtpCoordinator(
    val navController: NavController,
    val viewModel: OtpViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doUpdateOtp(otp: String) {
        viewModel.updateOtp(otp)
    }

    fun doUpdatePassword(password: String) {
        viewModel.updatePassword(password)
    }

    fun doUpdatePasswordConfirm(passwordConfirm: String) {
        viewModel.updatePasswordConfirm(passwordConfirm)
    }

    fun doUpdateEmail(email: String) {
        viewModel.updateEmail(email)
    }

    fun resetPassword() {
        viewModel.resetPassword()
    }

    fun navLogin() {

        navController.previousBackStackEntry?.savedStateHandle?.set(
            "email",
            viewModel.stateFlow.value.email
        )

        navController.popBackStack(Screens.Login.route, inclusive = false, saveState = true)
    }
}

@Composable
fun rememberOtpCoordinator(
    navController: NavController,
    viewModel: OtpViewModel = hiltViewModel()
): OtpCoordinator {
    return remember(viewModel) {
        OtpCoordinator(
            navController = navController,
            viewModel = viewModel
        )
    }
}