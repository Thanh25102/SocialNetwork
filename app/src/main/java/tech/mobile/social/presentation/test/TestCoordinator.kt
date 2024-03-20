package tech.mobile.social.presentation.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
/**
 * coordinator pattern which is responsible for handling navigation actions from the UI layer & ViewModel
 */
class TestCoordinator(
    val viewModel: TestViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberTestCoordinator(
    viewModel: TestViewModel = hiltViewModel()
): TestCoordinator {
    return remember(viewModel) {
        TestCoordinator(
            viewModel = viewModel
        )
    }
}