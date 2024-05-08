package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.NotificationsQuery
import tech.mobile.social.domain.repository.NotificationRepo
import tech.mobile.social.domain.usecase.interfaces.NotificationUseCase

class NotificationUseCaseImpl(private val notificationRepo: NotificationRepo) : NotificationUseCase {
    override suspend fun getNotifications(take: Optional<Int?>, after: Optional<String?>): ApolloResponse<NotificationsQuery.Data>? {
        return notificationRepo.getNotifications(take,after);
    }
}