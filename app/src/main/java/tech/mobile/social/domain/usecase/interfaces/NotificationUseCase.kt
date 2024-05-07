package tech.mobile.social.domain.usecase.interfaces

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.NotificationsQuery

interface NotificationUseCase {
    suspend fun getNotifications(take: Optional<Int?>, after: Optional<String?>): ApolloResponse<NotificationsQuery.Data>?
}