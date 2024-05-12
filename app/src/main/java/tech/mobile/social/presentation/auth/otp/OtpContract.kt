package tech.mobile.social.presentation.auth.otp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents OtpScreen
 **/
data class OtpState(
    val email: String = "",
    val otp: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
    val erMess: String = "",
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
)

/**
 * Otp Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class OtpActions(
    val onResetPassword: () -> Unit = {},
    val updateOtp: (String) -> Unit = {},
    val updateEmail: (String) -> Unit = {},
    val updatePassword: (String) -> Unit = {},
    val updatePasswordConfirm: (String) -> Unit = {},
    val navLogin: () -> Unit = {}
)