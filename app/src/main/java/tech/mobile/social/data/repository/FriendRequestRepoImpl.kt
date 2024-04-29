package tech.mobile.social.data.repository

import android.content.SharedPreferences
import androidx.compose.ui.graphics.vector.addPathNodes
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.http.HttpHeader
import com.apollographql.apollo3.exception.ApolloException
import okhttp3.internal.addHeaderLenient
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.PostQuery
import tech.mobile.social.RegisterMutation
import tech.mobile.social.data.mapper.toAuth
import tech.mobile.social.data.mapper.toFriendRequest
import tech.mobile.social.data.mapper.toUser
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.model.friend.FriendRequests
import tech.mobile.social.domain.repository.AuthorizeRepo
import tech.mobile.social.domain.repository.FriendRequestRepo
import tech.mobile.social.type.RequestWhereInput
//import tech.mobile.social.type.AuthorizeInput
//import tech.mobile.social.type.RequestWhereInput
//import tech.mobile.social.type.UserCreateInput
import tech.mobile.social.utils.handleException

//
class FriendRequestRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : FriendRequestRepo {

    override suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): Result<FriendRequests, DataError.ServerErrors> {
        return try {
            val result = apolloClient.newBuilder().apply { addHttpHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2MmQyZTEyYWM3NjMzODgwZjgwMDBjZiIsInVzZXJuYW1lIjoiY2hpdGhpbmgiLCJlbWFpbCI6ImFiY0BnbWFpbC5jb20iLCJpYXQiOjE3MTQzMjk1NzYsImV4cCI6MTcyMzMyOTU3Nn0.0JoeCakHWWqcdDUnExZVHLYT-5stmwoe9EZDzeKCotc") }.build()
                .query(FriendRequestQuery(take, after, filter))
                .execute()

            if (!result.hasErrors())
                Result.Success(result.data!!.toFriendRequest())
            else
                Result.Error(DataError.ServerErrors(handleException(result.errors!!)))
        } catch (e: ApolloException) {
            Result.Error(DataError.ServerErrors(listOf(e.toString())))
        }
    }
}