package tech.mobile.social.presentation.auth.otp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun OtpRoute(
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
            onClick = coordinator::doStuff
        )
    }
}