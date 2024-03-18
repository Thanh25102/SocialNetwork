package tech.mobile.social.presentation.app.notifications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents NotificationsScreen
 **/
class NotificationsState

/**
 * Notifications Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class NotificationsActions(
    val onClick: () -> Unit = {}
)