package tech.mobile.social.data.repository

//import tech.mobile.social.type.AuthorizeInput
//import tech.mobile.social.type.RequestWhereInput
//import tech.mobile.social.type.UserCreateInput
import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.network.ws.GraphQLWsProtocol
import com.apollographql.apollo3.network.ws.WebSocketNetworkTransport
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.data.mapper.toFriendRequest
import tech.mobile.social.data.mapper.toFriendRequests
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.friend.FriendRequest
import tech.mobile.social.domain.model.friend.FriendRequests
import tech.mobile.social.domain.repository.FriendRequestRepo
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput
import tech.mobile.social.utils.handleException

//
class FriendRequestRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : FriendRequestRepo {

    override suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): Result<FriendRequests, DataError.ServerErrors> {
        return try {
            val result = apolloClient.newBuilder().subscriptionNetworkTransport(
                WebSocketNetworkTransport.Builder()
                    .protocol(GraphQLWsProtocol.Factory())
                    .serverUrl("http://171.239.144.144:8334/graphql")
                    .build()
            ).apply { addHttpHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2MmQ1NjYwZWU1ZWUyZTY1NjBmMzg1NSIsInVzZXJuYW1lIjoia2lkcDJoIiwiZW1haWwiOiJraWRwMmhAZ21haWwuY29tIiwiaWF0IjoxNzE0NTAxNjg2LCJleHAiOjE3MjM1MDE2ODZ9.iOeQK81VJPce7yb7Ese7clwPj-RcKaTbXmj9WzIwGlw") }.build()
                .query(FriendRequestQuery(take, after, filter))
                .execute()

            if (!result.hasErrors())
                Result.Success(result.data!!.toFriendRequests())
            else
                Result.Error(DataError.ServerErrors(handleException(result.errors!!)))
        } catch (e: ApolloException) {
            Result.Error(DataError.ServerErrors(listOf(e.toString())))
        }
    }

    override suspend fun handleFriendRequest(
        requestId: String,
        status: RequestStatus
    ): Result<FriendRequest, DataError.ServerErrors> {
        return try {
            val result = apolloClient.newBuilder().apply { addHttpHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2MmQ1NjYwZWU1ZWUyZTY1NjBmMzg1NSIsInVzZXJuYW1lIjoia2lkcDJoIiwiZW1haWwiOiJraWRwMmhAZ21haWwuY29tIiwiaWF0IjoxNzE0NTAxNjg2LCJleHAiOjE3MjM1MDE2ODZ9.iOeQK81VJPce7yb7Ese7clwPj-RcKaTbXmj9WzIwGlw") }.build()
                .mutation(HandleRequestMutation(requestId, status))
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