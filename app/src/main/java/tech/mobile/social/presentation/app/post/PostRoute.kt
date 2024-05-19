package tech.mobile.social.presentation.app.post

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun PostRoute(
    state: PostState,
    coordinator: PostCoordinator = rememberPostCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.stateFlow.collectAsState()

    uiState.post = state

    // UI Actions
    val actions = rememberPostActions(coordinator)

    // UI Rendering
    PostScreen(uiState, actions)
}

@Composable
fun rememberPostActions(coordinator: PostCoordinator): PostActions {
    return remember(coordinator) {
        PostActions(
            onClick = coordinator::doStuff,
            onCloseComments = coordinator.viewModel::doCloseComments,
            onOpenComments = coordinator.viewModel::doOpenComments,
            onSharePost = coordinator.viewModel::sharePost,
            onReactionPost = coordinator.viewModel::reactionPost,
        )
    }
}