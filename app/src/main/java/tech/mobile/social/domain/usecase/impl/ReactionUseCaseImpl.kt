package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.ApolloResponse
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.ReactionAddedSubscription
import tech.mobile.social.domain.repository.ReactionRepo
import tech.mobile.social.domain.usecase.interfaces.ReactionUseCase

class ReactionUseCaseImpl(private val reactionRepo: ReactionRepo) : ReactionUseCase {
    override suspend fun handlerReactionAdded() : Flow<ApolloResponse<ReactionAddedSubscription.Data>>? {
        return reactionRepo.handlerReactionAdded()
    }
}