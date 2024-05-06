package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.type.Comment

interface CommentRepo {
//    suspend fun handleCommentAdded(postId: String) : Flow<ApolloResponse<CommentAddedSubscription.Data>>?
}