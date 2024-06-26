package tech.mobile.social.presentation.auth.login

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val email: String,
    val fullName: String?,
    val googleIdToken: String?,
    val profilePictureUrl: String?
)