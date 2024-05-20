package tech.mobile.social.presentation.app.posts

import tech.mobile.social.domain.model.post.User
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.Optional.*
import tech.mobile.social.type.UserCreateNestedOneWithoutPostsInput
import tech.mobile.social.type.UserCreateOrConnectWithoutPostsInput
import tech.mobile.social.type.UserCreateWithoutPostsInput
import tech.mobile.social.type.UserWhereUniqueInput
import java.time.LocalDateTime


/**
 * UI State that represents PostsScreen id, content, createdAt,createdBy
 **/
data class PostsState(
    val content: String = "",
)


/**
 * Posts Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class PostsActions(
    val onClick: () -> Unit = {},
    val onContentChange: (String) -> Unit = {}
)