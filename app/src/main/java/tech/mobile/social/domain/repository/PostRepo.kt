package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.Create_postMutation
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.PostQuery
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import tech.mobile.social.type.PostWhereInput
import tech.mobile.social.type.RequestWhereInput
import tech.mobile.social.type.UserCreateNestedOneWithoutPostsInput
import java.time.LocalDateTime

interface PostRepo {
    suspend fun GetPosts(): Result<Posts, DataError.ServerErrors>
    suspend fun CreatePost(id: Optional<String?>, content: String, createdAt: LocalDateTime): ApolloResponse<Create_postMutation.Data>
}