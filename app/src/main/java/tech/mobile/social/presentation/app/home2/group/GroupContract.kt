package tech.mobile.social.presentation.app.home2.group

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents GroupScreen
 **/
class GroupState

/**
 * Group Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class GroupActions(
    val onClick: () -> Unit = {}
)