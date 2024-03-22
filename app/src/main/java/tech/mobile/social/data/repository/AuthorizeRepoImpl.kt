package tech.mobile.social.data.repository

import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
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
import tech.mobile.social.utils.handleException

class AuthorizeRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : AuthorizeRepo {

    override suspend fun getAuthorize(username: String, password: String): Result<Auth, DataError.ServerErrors> {
        return try {
            val result = apolloClient
                .mutation(AuthorizeMutation(AuthorizeInput(password = username, username = password)))
                .execute()

            result.data?.authorize?.accessToken.also { token ->
                pref.edit().putString("token", token).apply()
            }

            if (!result.hasErrors())
                Result.Success(result.data!!.toAuth())
            else
                Result.Error(DataError.ServerErrors(handleException(result.errors!!)))
        } catch (e: ApolloException) {
            Result.Error(DataError.ServerErrors(listOf("Đã có lỗi xảy ra")))
        }
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