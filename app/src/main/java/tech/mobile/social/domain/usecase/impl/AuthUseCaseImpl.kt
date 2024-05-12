package tech.mobile.social.domain.usecase.impl

import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.repository.AuthorizeRepo
import tech.mobile.social.domain.usecase.interfaces.AuthUseCase

class AuthUseCaseImpl(private val authorizeRepo: AuthorizeRepo) : AuthUseCase {
    override suspend fun login(
        email: String,
        password: String
    ): Result<Auth, DataError.ServerErrors> {
        return authorizeRepo.login(email, password)
    }

    override suspend fun register(
        fullname: String,
        email: String,
        password: String
    ): Result<User, DataError.ServerErrors> {

        return authorizeRepo.register(fullname, email, password)
    }

}