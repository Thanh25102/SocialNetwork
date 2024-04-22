package tech.mobile.social.presentation.auth.otp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents OtpScreen
 **/
class OtpState

/**
 * Otp Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class OtpActions(
    val onClick: () -> Unit = {}
)