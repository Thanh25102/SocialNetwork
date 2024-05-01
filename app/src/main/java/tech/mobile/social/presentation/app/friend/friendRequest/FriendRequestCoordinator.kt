package tech.mobile.social.presentation.app.friend.friendRequest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import tech.mobile.social.FriendRequestQuery

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class FriendRequestCoordinator(
    val viewModel: FriendRequestViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun deleteFriendRequest(request: FriendRequestQuery.Edge) {
        viewModel.deleteFriendRequest(request)
    }

    fun acceptFriendRequest(requestId: String) {
        viewModel.acceptFriendRequest(requestId)
    }

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberFriendRequestCoordinator(
    viewModel: FriendRequestViewModel = hiltViewModel()
): FriendRequestCoordinator {
    return remember(viewModel) {
        FriendRequestCoordinator(
            viewModel = viewModel
        )
    }
}