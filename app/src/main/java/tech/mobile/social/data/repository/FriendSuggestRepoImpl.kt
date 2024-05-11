package tech.mobile.social.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.FriendSuggestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.RequestAddedSubscription
import tech.mobile.social.RequestFriendMutation
import tech.mobile.social.domain.repository.FriendSuggestRepo

class FriendSuggestRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : FriendSuggestRepo {
    override suspend fun getFriendSuggests(take: Optional<Int?>, after: Optional<String?>): ApolloResponse<FriendSuggestQuery.Data>? {
        try {
            val result = apolloClient
                .query(FriendSuggestQuery(take, after))
                .execute()
            if(result.hasErrors()) {
                val error = result.errors?.firstOrNull()
                Log.e("FriendSuggestRepoImpl", "getFriendRequests: ${error?.message}")
                return null;
            }
            return result;
        } catch (e: ApolloException) {
            Log.e("FriendSuggestRepoImpl", "getFriendRequests: ${e.message}")
            return null;
        }
    }
    override suspend fun handleSendFriendRequest(userId: String): ApolloResponse<RequestFriendMutation.Data>? {
        try {
            val result = apolloClient
                .mutation(RequestFriendMutation(userId))
                .execute()
            if(result.hasErrors()) {
                val error = result.errors?.firstOrNull()
                Log.e("FriendSuggestRepoImpl", "handleFriendRequest: ${error?.message}")
                return null;
            }
            return result;
        } catch (e: ApolloException) {
            Log.e("FriendSuggestRepoImpl", "handleFriendRequest: ${e.message}")
            return null;
        }
    }
}