package tech.mobile.social.domain.usecase.interfaces

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.FriendSuggestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.RequestAddedSubscription
import tech.mobile.social.RequestFriendMutation
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput

interface FriendSuggestUseCase {
    suspend fun getFriendSuggests(take: Optional<Int?>, after: Optional<String?>): ApolloResponse<FriendSuggestQuery.Data>?
    suspend fun handleSendFriendRequest(userId: String):  ApolloResponse<RequestFriendMutation.Data>?
}