package tech.mobile.social.presentation.auth.login2


/**
 * UI State that represents Login2Screen
 **/
data class Login2State(val email: String = "", val password: String = "")

/**
 * Login2 Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class Login2Actions(
    val onLogin: () -> Unit = {},
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {}
)