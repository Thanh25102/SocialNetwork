package tech.mobile.social.domain.usecase.interfaces

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.Create_postMutation
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.PostModel
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import tech.mobile.social.type.UserCreateNestedOneWithoutPostsInput
import java.time.LocalDateTime

interface PostUseCase {
    suspend fun Getpost(): Result<Posts, DataError.ServerErrors>

    suspend fun Createpost(id: Optional<String?>, content: String, createdAt: LocalDateTime, createdBy: UserCreateNestedOneWithoutPostsInput) : ApolloResponse<Create_postMutation.Data>
}