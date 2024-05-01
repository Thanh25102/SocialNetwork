package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.Optional
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.model.friend.FriendRequest
import tech.mobile.social.domain.model.friend.FriendRequests
import tech.mobile.social.domain.repository.AuthorizeRepo
import tech.mobile.social.domain.repository.FriendRequestRepo
import tech.mobile.social.domain.usecase.interfaces.AuthUseCase
import tech.mobile.social.domain.usecase.interfaces.FriendRequestUseCase
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput

class FriendRequestUseCaseImpl(private val friendRequestRepo: FriendRequestRepo) : FriendRequestUseCase {
    override suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): Result<FriendRequests, DataError.ServerErrors> {
        return friendRequestRepo.getFriendRequests(take, after, filter)
    }
    override suspend fun handleFriendRequest(requestId: String, status: RequestStatus): Result<FriendRequest, DataError.ServerErrors> {
        return friendRequestRepo.handleFriendRequest(requestId,status)
    }
}