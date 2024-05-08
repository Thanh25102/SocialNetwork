package tech.mobile.social.presentation.app.friend.friendSuggest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import tech.mobile.social.FriendRequestQuery

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class FriendSuggestCoordinator(
    val viewModel: FriendSuggestViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun deleteFriendSuggest(userId: String) {
        viewModel.deleteFriendRequest(userId)
    }

    fun sendFriendRequest(userId: String) {
        viewModel.sendFriendRequest(userId)
    }

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberFriendSuggestCoordinator(
    viewModel: FriendSuggestViewModel = hiltViewModel()
): FriendSuggestCoordinator {
    return remember(viewModel) {
        FriendSuggestCoordinator(
            viewModel = viewModel
        )
    }
}