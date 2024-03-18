package tech.mobile.social.presentation.app.home2.flowing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun FlowingRoute(
    coordinator: FlowingCoordinator = rememberFlowingCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(FlowingState())

    // UI Actions
    val actions = rememberFlowingActions(coordinator)

    // UI Rendering
    FlowingScreen(uiState, actions)
}


@Composable
fun rememberFlowingActions(coordinator: FlowingCoordinator): FlowingActions {
    return remember(coordinator) {
        FlowingActions(
            onClick = coordinator::doStuff
        )
    }
}