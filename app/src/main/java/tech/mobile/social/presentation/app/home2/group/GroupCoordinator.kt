package tech.mobile.social.presentation.app.home2.group

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class GroupCoordinator(
    val viewModel: GroupViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberGroupCoordinator(
    viewModel: GroupViewModel = hiltViewModel()
): GroupCoordinator {
    return remember(viewModel) {
        GroupCoordinator(
            viewModel = viewModel
        )
    }
}