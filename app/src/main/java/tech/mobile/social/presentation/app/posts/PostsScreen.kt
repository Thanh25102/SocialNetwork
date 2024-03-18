package tech.mobile.social.presentation.app.posts

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PostsScreen(
    state: PostsState,
    actions: PostsActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "Posts")
private fun PostsScreenPreview() {
    PostsScreen(
        state = PostsState(),
        actions = PostsActions()
    )
}

