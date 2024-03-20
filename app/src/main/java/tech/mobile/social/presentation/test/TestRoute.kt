package tech.mobile.social.presentation.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TestRoute(
    coordinator: TestCoordinator = rememberTestCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(TestState())

    // UI Actions
    val actions = rememberTestActions(coordinator)

    // UI Rendering
    TestScreen(uiState, actions)
}


@Composable
fun rememberTestActions(coordinator: TestCoordinator): TestActions {
    return remember(coordinator) {
        TestActions(
            onClick = coordinator::doStuff
        )
    }
}