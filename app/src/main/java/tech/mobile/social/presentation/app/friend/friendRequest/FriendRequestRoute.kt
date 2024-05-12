package tech.mobile.social.presentation.app.friend.friendRequest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun FriendRequestRoute(
    coordinator: FriendRequestCoordinator = rememberFriendRequestCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(FriendRequestState())

    // UI Actions
    val actions = rememberFriendActions(coordinator)

    // UI Rendering
    FriendRequestScreen(uiState, actions)
}


@Composable
fun rememberFriendActions(coordinator: FriendRequestCoordinator): FriendRequestActions {
    return remember(coordinator) {
        FriendRequestActions(
            onClick = coordinator::doStuff,
            onDeleteRequest = coordinator::deleteFriendRequest,
            onAcceptFriendRequest = coordinator::acceptFriendRequest,
            onScroll = coordinator.viewModel::loadNextItems
        )
    }
}