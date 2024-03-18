package tech.mobile.social.presentation.app.posts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class PostsCoordinator(
    val viewModel: PostsViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberPostsCoordinator(
    viewModel: PostsViewModel = hiltViewModel()
): PostsCoordinator {
    return remember(viewModel) {
        PostsCoordinator(
            viewModel = viewModel
        )
    }
}