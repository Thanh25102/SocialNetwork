package tech.mobile.social.presentation.app.home2.group

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun GroupRoute(
    coordinator: GroupCoordinator = rememberGroupCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(GroupState())

    // UI Actions
    val actions = rememberGroupActions(coordinator)

    // UI Rendering
    GroupScreen(uiState, actions)
}


@Composable
fun rememberGroupActions(coordinator: GroupCoordinator): GroupActions {
    return remember(coordinator) {
        GroupActions(
            onClick = coordinator::doStuff
        )
    }
}