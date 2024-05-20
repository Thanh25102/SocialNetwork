package tech.mobile.social.domain.usecase.interfaces

import com.apollographql.apollo3.api.ApolloResponse
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.ReactionAddedSubscription
import tech.mobile.social.ReactionPostMutation

interface ReactionUseCase {
    suspend fun handlerReactionAdded(): Flow<ApolloResponse<ReactionAddedSubscription.Data>>?

    suspend fun reactionPost(postId: String): ApolloResponse<ReactionPostMutation.Data>?
}