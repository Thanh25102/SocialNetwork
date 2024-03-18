package tech.mobile.social.presentation.app.home2.foryou

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents ForYouScreen
 **/
class ForYouState

/**
 * ForYou Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ForYouActions(
    val onClick: () -> Unit = {}
)