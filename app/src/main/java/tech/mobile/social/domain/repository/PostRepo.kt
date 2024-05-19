package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.CreatePostMutation
import tech.mobile.social.NewsfeedQuery
import tech.mobile.social.PostSharedSubscription
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.post.Post
import tech.mobile.social.domain.model.post.Posts
import tech.mobile.social.domain.model.post.User
import java.time.LocalDateTime

interface PostRepo {
    suspend fun GetPosts(): Result<Posts, DataError.ServerErrors>
    suspend fun CreatePost(id: Optional<String?>, content: Optional<String?>, file: Optional<Any?>): ApolloResponse<CreatePostMutation.Data>
    suspend fun NewsFeed(take: Optional<Int?>, after: Optional<String?>): ApolloResponse<NewsfeedQuery.Data>?
    suspend fun handlePostShared(): Flow<ApolloResponse<PostSharedSubscription.Data>>?
}