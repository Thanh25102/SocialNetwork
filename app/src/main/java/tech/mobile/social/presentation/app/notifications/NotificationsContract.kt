package tech.mobile.social.presentation.app.notifications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.NotificationsQuery
import tech.mobile.social.fragment.CommentNotification
import tech.mobile.social.fragment.PostNotification
import tech.mobile.social.fragment.RecipientNotification
import tech.mobile.social.fragment.RequestNotification
import tech.mobile.social.fragment.SenderNotification
import tech.mobile.social.type.NotificationType
import java.util.Optional


/**
 * UI State that represents NotificationsScreen
 **/

data class NotificationsState (
    val notifications: List<Notification>? = null,
    var isLoading: Boolean = false,
    val error: String = "",
)

class Notification(
    val id: String,
    val type: NotificationType,
    val createdAt: Any?,
    val senderNotification: SenderNotification? = null,
    val commentNotification: CommentNotification? = null,
    val recipientsNotification: RecipientNotification? = null,
    val requestNotification: RequestNotification? = null,
    val postNotification: PostNotification? = null,


    )

/**
 * Notifications Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class NotificationsActions(
    val onClick: () -> Unit = {}
)