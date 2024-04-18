package tech.mobile.social.presentation.app.posts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.ai.client.generativeai.common.shared.Content

import tech.mobile.social.domain.model.post.User
import java.time.LocalDateTime

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class PostsCoordinator(
    val viewModel: PostsViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff(id: String, content: String, createdAt: java.time.LocalDateTime, createdBy: User) {
        viewModel.createPost(id,content,createdAt,createdBy)

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