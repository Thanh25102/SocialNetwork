package tech.mobile.social.presentation.app.posts

import tech.mobile.social.domain.model.post.User
import java.time.LocalDateTime


/**
 * UI State that represents PostsScreen id, content, createdAt,createdBy
 **/
data class PostsState(
    val id: String = "",
    val content: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val createdBy: User = User(id = "", username = ""),
    val image: String? = null
)

/**
 * Posts Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class PostsActions(
    val onClick: () -> Unit = {}
)