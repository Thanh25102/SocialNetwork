package tech.mobile.social.presentation.auth.forgot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents ForgotScreen
 **/
data class ForgotState(
    val email: String = "",
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val erMess: String = ""
)

/**
 * Forgot Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ForgotActions(
    val onForgotPassword: () -> Unit = {},
    val updateEmail: (String) -> Unit = {},
    val navResetPassword: () -> Unit = {}
)