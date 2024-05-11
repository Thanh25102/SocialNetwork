package tech.mobile.social.domain.repository;


import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.CommentsQuery
import tech.mobile.social.type.CommentWhereInput

interface CommentRepo {
    suspend fun getComments(take: Optional<Int?>, after: Optional<String?>, filter: Optional<CommentWhereInput?>):  ApolloResponse<CommentsQuery.Data>?
    suspend fun handleCommentAdded() : Flow<ApolloResponse<CommentAddedSubscription.Data>>?
}