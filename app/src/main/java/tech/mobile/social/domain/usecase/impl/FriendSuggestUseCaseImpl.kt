package tech.mobile.social.domain.usecase.impl

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.FriendSuggestQuery
import tech.mobile.social.RequestFriendMutation
import tech.mobile.social.domain.repository.FriendSuggestRepo
import tech.mobile.social.domain.usecase.interfaces.FriendSuggestUseCase

class FriendSuggestUseCaseImpl(private val friendSuggestRepo: FriendSuggestRepo) : FriendSuggestUseCase {
    override suspend fun getFriendSuggests(take: Optional<Int?>, after: Optional<String?>): ApolloResponse<FriendSuggestQuery.Data>? {
        return friendSuggestRepo.getFriendSuggests(take, after);
    }

    override suspend fun handleSendFriendRequest(userId: String): ApolloResponse<RequestFriendMutation.Data>? {
        return friendSuggestRepo.handleSendFriendRequest(userId);
    }
}