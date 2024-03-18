package tech.mobile.social.presentation.app.home2.flowing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class FlowingCoordinator(
    val viewModel: FlowingViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberFlowingCoordinator(
    viewModel: FlowingViewModel = hiltViewModel()
): FlowingCoordinator {
    return remember(viewModel) {
        FlowingCoordinator(
            viewModel = viewModel
        )
    }
}