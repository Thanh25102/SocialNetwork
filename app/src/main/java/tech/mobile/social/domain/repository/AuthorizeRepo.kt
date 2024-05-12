package tech.mobile.social.domain.repository

import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User

interface AuthorizeRepo {
//    suspend fun getUserCurrent(
//    ): Result<Auth, DataError.ServerErrors>

    suspend fun login(
        email: String,
        password: String
    ): Result<Auth, DataError.ServerErrors>

    suspend fun register(
        fullname: String,
        password: String,
        email: String
    ): Result<User, DataError.ServerErrors>

    suspend fun forgot(
        email: String
    ): Result<Boolean, DataError.ServerErrors>

    suspend fun resetPassword(
        email: String,
        otp: String,
        password: String
    ): Result<Boolean, DataError.ServerErrors>
}