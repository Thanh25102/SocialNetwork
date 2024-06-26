package tech.mobile.social.domain.model.auth

data class Auth(
    val accessToken: String,
    val user: User,
)

data class User(
    val id: String,
    val username: String?,
    val email: String?,
    val fullname: String?,
)