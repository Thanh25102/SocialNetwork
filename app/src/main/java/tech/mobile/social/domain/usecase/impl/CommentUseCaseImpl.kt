package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.CommentsQuery
import tech.mobile.social.domain.repository.CommentRepo
import tech.mobile.social.domain.usecase.interfaces.CommentUseCase
import tech.mobile.social.type.CommentWhereInput

class CommentUseCaseImpl(private val commentRepo: CommentRepo) : CommentUseCase {
    override suspend fun getComments(
        take: Optional<Int?>,
        after: Optional<String?>,
        filter: Optional<CommentWhereInput?>
    ): ApolloResponse<CommentsQuery.Data>? {
        return commentRepo.getComments(take, after, filter)
    }
    override suspend fun handleCommentAdded(): Flow<ApolloResponse<CommentAddedSubscription.Data>>? {
        return commentRepo.handleCommentAdded()
    }
}