package tech.mobile.social.domain.usecase.interfaces

import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User

interface AuthUseCase {
    suspend fun login(email: String, password: String): Result<Auth, DataError.ServerErrors>
    suspend fun register(
        fullname: String,
        email: String,
        password: String
    ): Result<User, DataError.ServerErrors>
}