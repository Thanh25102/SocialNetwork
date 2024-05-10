package tech.mobile.social.domain.usecase.interfaces

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.CommentsQuery
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.type.CommentWhereInput
import tech.mobile.social.type.RequestWhereInput

interface CommentUseCase {
    suspend fun getComments(take: Optional<Int?>, after: Optional<String?>, filter: Optional<CommentWhereInput?>): ApolloResponse<CommentsQuery.Data>?
}