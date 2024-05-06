package tech.mobile.social.data.repository


import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.flow.Flow

import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.RequestAddedSubscription

import tech.mobile.social.domain.repository.FriendRequestRepo
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput

//
class FriendRequestRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : FriendRequestRepo {

    override suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): ApolloResponse<FriendRequestQuery.Data>? {
        return try {
            val result = apolloClient
                .query(FriendRequestQuery(take, after, filter))
                .execute()
            if(result.hasErrors()) {
                val error = result.errors?.firstOrNull()
                Log.e("FriendRequestRepoImpl", "getFriendRequests: ${error?.message}")
                return null;
            }
            return result;
        } catch (e: ApolloException) {
            Log.e("FriendRequestRepoImpl", "getFriendRequests: ${e.message}")
            return null;
        }
    }

    override suspend fun handleFriendRequest(
        requestId: String,
        status: RequestStatus
    ):  ApolloResponse<HandleRequestMutation.Data>? {
        try {
            val result = apolloClient
                .mutation(HandleRequestMutation(requestId, status))
                .execute()
            if(result.hasErrors()) {
                val error = result.errors?.firstOrNull()
                Log.e("FriendRequestRepoImpl", "handleFriendRequest: ${error?.message}")
                return null;
            }
           return result;
        } catch (e: ApolloException) {
            Log.e("FriendRequestRepoImpl", "handleFriendRequest: ${e.message}")
            return null;
        }
    }

    override suspend fun requestAdded(): Flow<ApolloResponse<RequestAddedSubscription.Data>>? {
        try {
            val result = apolloClient
                .subscription(RequestAddedSubscription())
                .toFlow()
            return result;
        } catch (e: ApolloException) {
            Log.e("FriendRequestRepoImpl", "handleFriendRequest: ${e.message}")
            return null;
        }
    }
}