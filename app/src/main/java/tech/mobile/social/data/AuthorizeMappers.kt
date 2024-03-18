package tech.mobile.social.data

import tech.mobile.social.AuthorizeMutation
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User

fun AuthorizeMutation.Data.toAuth(): Auth {
    return Auth(
        accessToken = authorize?.accessToken ?: "",
        user = User(authorize.user.id)
    )
}