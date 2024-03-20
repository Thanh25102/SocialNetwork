package tech.mobile.social.data.repository

import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import tech.mobile.social.AuthorizeMutation
import tech.mobile.social.RegisterMutation
import tech.mobile.social.data.mapper.toAuth
import tech.mobile.social.data.mapper.toUser
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.repository.AuthorizeRepo
import tech.mobile.social.type.AuthorizeInput
import tech.mobile.social.type.UserCreateInput

class AuthorizeRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : AuthorizeRepo {

    override suspend fun getAuthorize(username: String, password: String): Result<Auth, DataError.ServerErrors> {
        val result = apolloClient
            .mutation(AuthorizeMutation(AuthorizeInput(username, password)))
            .execute()

        if (result.errors === null) {
            val token = result.data?.authorize?.accessToken
            token?.let {
                pref.edit().putString("token", it).apply()
            }
        }
        return result.errors?.let { errors ->
            Result.Error(DataError.ServerErrors(errors.map { it.message }))
        } ?: Result.Success(result.data?.toAuth() ?: Auth("", User("", null, null)))
    }

    override suspend fun authorize(
        username: String,
        password: String,
        email: String
    ): Result<User, DataError.ServerErrors> {
        val result = apolloClient.mutation(
            RegisterMutation(
                UserCreateInput(
                    username = username,
                    password = password,
                    email = email
                )
            )
        ).execute()

        return result.errors?.let { errors ->
            Result.Error(DataError.ServerErrors(errors.map { it.message }))
        } ?: Result.Success(result.data?.toUser() ?: User(id = "", null, null))

    }
}