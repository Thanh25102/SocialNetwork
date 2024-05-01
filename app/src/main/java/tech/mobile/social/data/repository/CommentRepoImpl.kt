package tech.mobile.social.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.network.ws.GraphQLWsProtocol
import com.apollographql.apollo3.network.ws.SubscriptionWsProtocol
import com.apollographql.apollo3.network.ws.WebSocketNetworkTransport
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.data.mapper.toFriendRequests
import tech.mobile.social.domain.DataError
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.repository.CommentRepo
import tech.mobile.social.type.Comment
import tech.mobile.social.utils.handleException

class CommentRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : CommentRepo {
    override suspend fun handleCommentAdded(postId: String) {
//        return
        try {
            val result = apolloClient.newBuilder()
                .apply { addHttpHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2MmQ1NjYwZWU1ZWUyZTY1NjBmMzg1NSIsInVzZXJuYW1lIjoia2lkcDJoIiwiZW1haWwiOiJraWRwMmhAZ21haWwuY29tIiwiaWF0IjoxNzE0NTAxNjg2LCJleHAiOjE3MjM1MDE2ODZ9.iOeQK81VJPce7yb7Ese7clwPj-RcKaTbXmj9WzIwGlw") }.build()
                .subscription(CommentAddedSubscription(postId))
                .toFlow()
                .collect{
                    Log.d("Test real time", it.data.toString())
                }
//            Log.d("Test real time", it.data.toString())
            Log.d("Test real time 2", "hehe")
//            Result.Success(emptyList())
        } catch (e: ApolloException) {
            Log.d("real time error", e.stackTraceToString())
        }

    }
}