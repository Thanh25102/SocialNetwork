package tech.mobile.social.presentation.app.home2.flowing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents FlowingScreen
 **/
class FlowingState

/**
 * Flowing Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class FlowingActions(
    val onClick: () -> Unit = {}
)