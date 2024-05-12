package tech.mobile.social.data.mapper

import tech.mobile.social.AuthorizeMutation
import tech.mobile.social.RegisterMutation
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User

fun AuthorizeMutation.Data.toAuth(): Auth {
    return Auth(
        accessToken = authorize.accessToken ?: "",
        user = User(
            authorize.user.id,
            authorize.user.username,
            authorize.user.email,
            authorize.user.fullname
        )
    )
}

fun RegisterMutation.Data.toUser(): User {
    return User(
        email = register?.email ?: "",
        username = register?.username ?: "",
        id = register?.id ?: "",
        fullname = register?.fullname ?: ""
    )
}