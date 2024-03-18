package tech.mobile.social.presentation.app.posts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun PostsRoute(
    coordinator: PostsCoordinator = rememberPostsCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(PostsState())

    // UI Actions
    val actions = rememberPostsActions(coordinator)

    // UI Rendering
    PostsScreen(uiState, actions)
}


@Composable
fun rememberPostsActions(coordinator: PostsCoordinator): PostsActions {
    return remember(coordinator) {
        PostsActions(
            onClick = coordinator::doStuff
        )
    }
}