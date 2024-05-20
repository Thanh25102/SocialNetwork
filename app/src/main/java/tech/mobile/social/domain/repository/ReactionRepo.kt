package tech.mobile.social.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import kotlinx.coroutines.flow.Flow
import tech.mobile.social.AddCommentMutation
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.ReactionAddedSubscription
import tech.mobile.social.ReactionPostMutation

interface ReactionRepo {
    suspend fun handlerReactionAdded() : Flow<ApolloResponse<ReactionAddedSubscription.Data>>?

    suspend fun reactionPost(postId: String):ApolloResponse<ReactionPostMutation.Data>?
}