package tech.mobile.social.presentation.app.friend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class FriendCoordinator(
    val viewModel: FriendViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberFriendCoordinator(
    viewModel: FriendViewModel = hiltViewModel()
): FriendCoordinator {
    return remember(viewModel) {
        FriendCoordinator(
            viewModel = viewModel
        )
    }
}