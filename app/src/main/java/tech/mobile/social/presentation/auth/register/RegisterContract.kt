package tech.mobile.social.presentation.auth.register


/**
 * UI State that represents RegisterScreen
 **/
data class RegisterState(
    val fullname: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val erMess: String = ""
)

/**
 * Register Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class RegisterActions(
    val goBack: () -> Unit = {},
    val onRegister: () -> Unit = {},
    val onNameChange: (name: String) -> Unit = {},
    val onEmailChange: (email: String) -> Unit = {},
    val onPasswordChange: (password: String) -> Unit = {},
    val onPasswordConfirmChange: (passwordConfirm: String) -> Unit = {},
)