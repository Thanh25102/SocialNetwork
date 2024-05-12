package tech.mobile.social.domain.usecase.interfaces

import com.apollographql.apollo3.api.ApolloResponse
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.ReactionAddedSubscription

interface ReactionUseCase {
    suspend fun handlerReactionAdded() : Flow<ApolloResponse<ReactionAddedSubscription.Data>>?
}