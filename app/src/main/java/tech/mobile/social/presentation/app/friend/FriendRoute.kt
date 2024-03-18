package tech.mobile.social.presentation.app.friend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun FriendRoute(
    coordinator: FriendCoordinator = rememberFriendCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(FriendState())

    // UI Actions
    val actions = rememberFriendActions(coordinator)

    // UI Rendering
    FriendScreen(uiState, actions)
}


@Composable
fun rememberFriendActions(coordinator: FriendCoordinator): FriendActions {
    return remember(coordinator) {
        FriendActions(
            onClick = coordinator::doStuff
        )
    }
}