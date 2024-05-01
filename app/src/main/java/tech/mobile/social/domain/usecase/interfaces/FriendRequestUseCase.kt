package tech.mobile.social.domain.usecase.interfaces

import com.apollographql.apollo3.api.Optional
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.friend.FriendRequest
import tech.mobile.social.domain.model.friend.FriendRequests
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput

interface FriendRequestUseCase {
    suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): Result<FriendRequests, DataError.ServerErrors>
    suspend fun handleFriendRequest(requestId: String, status: RequestStatus): Result<FriendRequest, DataError.ServerErrors>;
}