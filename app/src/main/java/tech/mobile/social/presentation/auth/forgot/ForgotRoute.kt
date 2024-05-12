package tech.mobile.social.presentation.auth.forgot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun ForgotRoute(
    navController: NavController,
    coordinator: ForgotCoordinator = rememberForgotCoordinator(navController = navController),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(ForgotState())

    // UI Actions
    val actions = rememberForgotActions(coordinator)

    // UI Rendering
    ForgotScreen(uiState, actions)
}


@Composable
fun rememberForgotActions(coordinator: ForgotCoordinator): ForgotActions {
    return remember(coordinator) {
        ForgotActions(
            onForgotPassword = coordinator::doForgotPassword,
            updateEmail = coordinator::doUpdateEmail,
            navResetPassword = coordinator::navResetPassword,
        )
    }
}