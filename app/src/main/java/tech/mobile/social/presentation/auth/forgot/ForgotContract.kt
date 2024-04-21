package tech.mobile.social.presentation.auth.forgot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents ForgotScreen
 **/
class ForgotState

/**
 * Forgot Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ForgotActions(
    val onClick: () -> Unit = {}
)