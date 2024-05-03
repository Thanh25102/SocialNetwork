package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.domain.repository.FriendRequestRepo
import tech.mobile.social.domain.usecase.interfaces.FriendRequestUseCase
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput

class FriendRequestUseCaseImpl(private val friendRequestRepo: FriendRequestRepo) : FriendRequestUseCase {
    override suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): ApolloResponse<FriendRequestQuery.Data>? {
        return friendRequestRepo.getFriendRequests(take, after, filter)
    }
    override suspend fun handleFriendRequest(requestId: String, status: RequestStatus):  ApolloResponse<HandleRequestMutation.Data>? {
        return friendRequestRepo.handleFriendRequest(requestId,status)
    }
}