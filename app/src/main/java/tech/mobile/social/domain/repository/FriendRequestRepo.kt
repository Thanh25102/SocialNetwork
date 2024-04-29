package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.Optional
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.model.friend.FriendRequests
import tech.mobile.social.type.RequestWhereInput


interface FriendRequestRepo {
    suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): Result<FriendRequests, DataError.ServerErrors>
}