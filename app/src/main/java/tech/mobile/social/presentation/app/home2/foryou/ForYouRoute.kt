package tech.mobile.social.presentation.app.home2.foryou

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun ForYouRoute(
    coordinator: ForYouCoordinator = rememberForYouCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(ForYouState())

    // UI Actions
    val actions = rememberForYouActions(coordinator)

    // UI Rendering
    ForYouScreen(uiState, actions)
}


@Composable
fun rememberForYouActions(coordinator: ForYouCoordinator): ForYouActions {
    return remember(coordinator) {
        ForYouActions(
            onClick = coordinator::doStuff
        )
    }
}