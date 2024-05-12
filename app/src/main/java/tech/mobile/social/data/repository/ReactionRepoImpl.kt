package tech.mobile.social.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.ReactionAddedSubscription
import tech.mobile.social.domain.repository.ReactionRepo

class ReactionRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : ReactionRepo {
    override suspend fun handlerReactionAdded(): Flow<ApolloResponse<ReactionAddedSubscription.Data>>? {
        try {
            val result = apolloClient
                .subscription(ReactionAddedSubscription()).toFlow();
            return result;
        } catch (e: ApolloException) {
            Log.d("real time error", e.stackTraceToString())
            return null;
        }
    }
}
