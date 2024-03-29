package tech.mobile.social.presentation.app.notifications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class NotificationsCoordinator(
    val viewModel: NotificationsViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberNotificationsCoordinator(
    viewModel: NotificationsViewModel = hiltViewModel()
): NotificationsCoordinator {
    return remember(viewModel) {
        NotificationsCoordinator(
            viewModel = viewModel
        )
    }
}