package tech.mobile.social.presentation.app.friend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents FriendScreen
 **/
class FriendState

/**
 * Friend Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class FriendActions(
    val onClick: () -> Unit = {}
)