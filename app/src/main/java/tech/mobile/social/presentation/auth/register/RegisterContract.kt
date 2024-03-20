package tech.mobile.social.presentation.auth.register


/**
 * UI State that represents RegisterScreen
 **/
data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = ""
)

/**
 * Register Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class RegisterActions(
    val onRegister: () -> Unit = {},
    val onNameChange: (name: String) -> Unit = {},
    val onEmailChange: (email: String) -> Unit = {},
    val onPasswordChange: (password: String) -> Unit = {},
    val onPasswordConfirmChange: (passwordConfirm: String) -> Unit = {},
)