package tech.mobile.social.presentation.app.friend.friendSuggest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun FriendSuggestRoute(
    coordinator: FriendSuggestCoordinator = rememberFriendSuggestCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(FriendSuggestState())

    // UI Actions
    val actions = rememberFriendActions(coordinator)

    // UI Rendering
    FriendRequestScreen(uiState, actions)
}


@Composable
fun rememberFriendActions(coordinator: FriendSuggestCoordinator): FriendSuggestActions {
    return remember(coordinator) {
        FriendSuggestActions(
            onClick = coordinator::doStuff,
            onDeleteSuggest = coordinator::deleteFriendSuggest,
            onSendFriendRequest = coordinator::sendFriendRequest
        )
    }
}