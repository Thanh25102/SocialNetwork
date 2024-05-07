package tech.mobile.social.presentation.app.posts

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
val userId = Absent
val userWhereUnique = UserWhereUniqueInput(id = userId)
val userCreate = UserCreateWithoutPostsInput(username = "", password = "", email = "")





val tempUserInput = UserCreateNestedOneWithoutPostsInput(
    connect = Optional.Present(UserWhereUniqueInput(userId)),

)




data class PostsState(
    val id: Optional<String?> = Absent,
    val content: String ="",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val createdBy: UserCreateNestedOneWithoutPostsInput = tempUserInput
)

/**
 * Posts Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class PostsActions(
    val onClick: () -> Unit = {}
)