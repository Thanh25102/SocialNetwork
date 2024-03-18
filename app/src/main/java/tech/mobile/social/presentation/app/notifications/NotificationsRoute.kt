package tech.mobile.social.presentation.app.notifications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun NotificationsRoute(
    coordinator: NotificationsCoordinator = rememberNotificationsCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(NotificationsState())

    // UI Actions
    val actions = rememberNotificationsActions(coordinator)

    // UI Rendering
    NotificationsScreen(uiState, actions)
}


@Composable
fun rememberNotificationsActions(coordinator: NotificationsCoordinator): NotificationsActions {
    return remember(coordinator) {
        NotificationsActions(
            onClick = coordinator::doStuff
        )
    }
}