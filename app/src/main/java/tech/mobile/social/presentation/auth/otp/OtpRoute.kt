package tech.mobile.social.presentation.auth.otp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun OtpRoute(
    navController: NavController,
    coordinator: OtpCoordinator = rememberOtpCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(OtpState())

    // UI Actions
    val actions = rememberOtpActions(coordinator)

    // UI Rendering
    OtpScreen(uiState, actions)
}


@Composable
fun rememberOtpActions(coordinator: OtpCoordinator): OtpActions {
    return remember(coordinator) {
        OtpActions(
            updateOtp = coordinator::doUpdateOtp,
            updatePassword = coordinator::doUpdatePassword,
            updatePasswordConfirm = coordinator::doUpdatePasswordConfirm,
            updateEmail = coordinator::doUpdateEmail,
            onResetPassword = coordinator::resetPassword
        )
    }
}