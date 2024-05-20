package tech.mobile.social.data.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import tech.mobile.social.AuthorizeMutation
import tech.mobile.social.Forgot_passwordMutation
import tech.mobile.social.RegisterMutation
import tech.mobile.social.ResetPasswordMutation
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
    private val apolloClient: ApolloClient, private val pref: SharedPreferences
) : AuthorizeRepo {

    @SuppressLint("CommitPrefEdits")
    override suspend fun login(
        email: String,
        password: String
    ): Result<Auth, DataError.ServerErrors> {
        try {
            val result =
                apolloClient.mutation(
                    AuthorizeMutation(
                        AuthorizeInput(
                            password = password,
                            username = email
                        )
                    )
                ).execute()

            result.data?.authorize?.accessToken.also { token ->
                pref.edit().putString("token", token).apply()
                pref.edit().putString("id", result.data?.authorize?.user?.id).apply()
            }

            if (!result.hasErrors()) {
                return Result.Success(result.data!!.toAuth())
            } else {
                return Result.Error(DataError.ServerErrors(handleException(result.errors!!)))
            }
        } catch (e: ApolloException) {
            return Result.Error(DataError.ServerErrors(listOf("Đã có lỗi xảy ra")))
        }
    }

    override suspend fun register(
        fullname: String,
        password: String,
        email: String
    ): Result<User, DataError.ServerErrors> {
        try {
            val result = apolloClient.mutation(
                RegisterMutation(
                    UserCreateInput(
                        fullname = fullname,
                        password = password,
                        email = email,
                        username = email
                    )
                )
            ).execute()

            if (result.errors != null) {
                Log.e("register", result.errors.toString())
                val serverErrors = result.errors!!.map { it.message }
                return Result.Error(DataError.ServerErrors(serverErrors))
            }

            val user = result.data!!.toUser()

            return Result.Success(user)
        } catch (e: ApolloException) {
            return Result.Error(DataError.ServerErrors(listOf("Đã có lỗi xảy ra")))
        }
    }

    override suspend fun forgot(email: String): Result<Boolean, DataError.ServerErrors> {
        try {
            val result = apolloClient.mutation(
                Forgot_passwordMutation(
                    email
                )
            ).execute()

            if (result.errors != null) {
                Log.e("register", result.errors.toString())
                val serverErrors = result.errors!!.map { it.message }
                return Result.Error(DataError.ServerErrors(serverErrors))
            }

            return Result.Success(true)

        } catch (e: ApolloException) {
            return Result.Error(DataError.ServerErrors(listOf("Đã có lỗi xảy ra")))
        }
    }

    override suspend fun resetPassword(
        email: String,
        otp: String,
        password: String
    ): Result<Boolean, DataError.ServerErrors> {
        try {
            val result = apolloClient.mutation(
                ResetPasswordMutation(
                    email,
                    otp,
                    password
                )
            ).execute()

            if (result.errors != null) {
                Log.e("register", result.errors.toString())
                val serverErrors = result.errors!!.map { it.message }
                return Result.Error(DataError.ServerErrors(serverErrors))
            }

            return Result.Success(true)

        } catch (e: ApolloException) {
            return Result.Error(DataError.ServerErrors(listOf("Đã có lỗi xảy ra")))
        }
    }

    override fun logout() {
        pref.edit().remove("token").apply()
    }
}