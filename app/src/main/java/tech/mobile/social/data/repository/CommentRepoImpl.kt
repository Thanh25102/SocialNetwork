package tech.mobile.social.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.domain.repository.CommentRepo

class CommentRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : CommentRepo {
    override suspend fun handleCommentAdded(): Flow<ApolloResponse<CommentAddedSubscription.Data>>? {
        try {
            val result = apolloClient
                .subscription(CommentAddedSubscription()).toFlow();
            return result;
        } catch (e: ApolloException) {
            Log.d("real time error", e.stackTraceToString())
            return null;
        }

    }
}