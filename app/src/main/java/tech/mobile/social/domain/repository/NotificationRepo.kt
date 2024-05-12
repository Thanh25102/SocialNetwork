package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.NotificationsQuery

interface NotificationRepo {
    suspend fun getNotifications(take: Optional<Int?>, after: Optional<String?>): ApolloResponse<NotificationsQuery.Data>?
}