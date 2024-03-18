package tech.mobile.social.presentation.app.profiles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents ProfilesScreen
 **/
class ProfilesState

/**
 * Profiles Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ProfilesActions(
    val onClick: () -> Unit = {}
)