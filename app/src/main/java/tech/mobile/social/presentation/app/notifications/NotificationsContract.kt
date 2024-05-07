package tech.mobile.social.presentation.app.notifications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.NotificationsQuery


/**
 * UI State that represents NotificationsScreen
 **/

class NotificationsState (
    val notifications: List<NotificationsQuery.Node>? = null,
    var isLoading: Boolean = false,
    val error: String = "",
)

/**
 * Notifications Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class NotificationsActions(
    val onClick: () -> Unit = {}
)