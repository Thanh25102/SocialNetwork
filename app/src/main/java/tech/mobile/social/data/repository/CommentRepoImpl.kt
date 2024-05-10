package tech.mobile.social.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.CommentsQuery
import tech.mobile.social.domain.repository.CommentRepo
import tech.mobile.social.type.CommentWhereInput

class CommentRepoImpl(
    private val apolloClient: ApolloClient,
    private val pref: SharedPreferences
) : CommentRepo {
    override suspend fun getComments(
        take: Optional<Int?>,
        after: Optional<String?>,
        filter: Optional<CommentWhereInput?>
    ): ApolloResponse<CommentsQuery.Data>? {
        return try {
            val result = apolloClient
                .query(CommentsQuery(take, after, filter))
                .execute()
            if(result.hasErrors()) {
                val error = result.errors?.firstOrNull()
                return null;
            }
            return result;
        } catch (e: ApolloException) {
            return null;
        }
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