package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.ApolloResponse
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.domain.repository.CommentRepo
import tech.mobile.social.domain.usecase.interfaces.CommentUseCase

class CommentUseCaseImpl(private val commentAddedRepo: CommentRepo) : CommentUseCase {
    override suspend fun handleCommentAdded(): Flow<ApolloResponse<CommentAddedSubscription.Data>>? {
        return commentAddedRepo.handleCommentAdded()
    }
}