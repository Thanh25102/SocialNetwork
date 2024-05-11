package tech.mobile.social.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.NotificationsQuery
import tech.mobile.social.domain.repository.NotificationRepo

class NotificationRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : NotificationRepo {
    override suspend fun getNotifications(
        take: Optional<Int?>,
        after: Optional<String?>
    ): ApolloResponse<NotificationsQuery.Data>? {
        try {
            val result = apolloClient
                .query(NotificationsQuery(take,after))
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
}