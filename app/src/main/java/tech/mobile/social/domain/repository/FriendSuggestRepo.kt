package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.FriendSuggestQuery
import tech.mobile.social.RequestFriendMutation

interface FriendSuggestRepo {
    suspend fun getFriendSuggests(take: Optional<Int?>, after: Optional<String?>): ApolloResponse<FriendSuggestQuery.Data>?
    suspend fun handleSendFriendRequest(userId: String):  ApolloResponse<RequestFriendMutation.Data>?
}