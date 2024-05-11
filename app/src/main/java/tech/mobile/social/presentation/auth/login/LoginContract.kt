package tech.mobile.social.presentation.auth.login


/**
 * UI State that represents Login2Screen
 **/
data class LoginState(val email: String = "", val password: String = "")

/**
 * Login2 Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LoginActions(
    val onLogin: () -> Unit = {},
    val onLoginGoogle: (GoogleAuthUiClient) -> Unit = {},
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val navRegister: () -> Unit = {}
)