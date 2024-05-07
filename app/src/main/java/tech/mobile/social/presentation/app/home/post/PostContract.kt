package tech.mobile.social.presentation.app.home.post

import java.time.LocalDateTime
import java.util.*


/**
 * UI State that represents PostScreen
 **/
data class State(
    val likes: Int,
    val sheetState: Boolean = false,
    val isLiked: Boolean,
    val friends: List<String>,
    val commentsCount: Int,
    val comments: List<Comment>,
    var post: PostState? = null
)

data class Comment(
    val author: String,
    val content: String,
    val time: LocalDateTime,
    val avatarResource: Int? = null
)

data class PostState(
    var avatarResource: Int? = null,
    var authorName: String = "",
    var postTime: Date? = null,
    var content: String = "",
    var imageResource: Int? = null,
    var sheetState: Boolean = false,
    var image: String? = null
)

/**
 * Post Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class PostActions(
    val onClick: () -> Unit = {},
    val onOpenComments: () -> Unit = {},
    val onCloseComments: () -> Unit = {},
)