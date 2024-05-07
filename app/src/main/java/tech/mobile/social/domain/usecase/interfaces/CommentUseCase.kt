package tech.mobile.social.domain.usecase.interfaces

import com.apollographql.apollo3.api.ApolloResponse
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription

interface CommentUseCase {
    suspend fun handleCommentAdded() : Flow<ApolloResponse<CommentAddedSubscription.Data>>?
}