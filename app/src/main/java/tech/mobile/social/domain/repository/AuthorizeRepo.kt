package tech.mobile.social.domain.repository

import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User

interface AuthorizeRepo {
    suspend fun getAuthorize(username: String, password: String): Result<Auth, DataError.ServerErrors>
    suspend fun authorize(username: String, password: String, email: String): Result<User, DataError.ServerErrors>
}