package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.RequestAddedSubscription
import tech.mobile.social.RequestHandledSubscription
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput


interface FriendRequestRepo {
    suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): ApolloResponse<FriendRequestQuery.Data>?
    suspend fun handleFriendRequest(requestId: String, status: RequestStatus):  ApolloResponse<HandleRequestMutation.Data>?
    suspend fun requestAdded(): Flow<ApolloResponse<RequestAddedSubscription.Data>>?
    suspend fun requestHandled(): Flow<ApolloResponse<RequestHandledSubscription.Data>>?
}