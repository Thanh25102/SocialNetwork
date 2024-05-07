package tech.mobile.social.presentation.app.friend.friendSuggest

import com.apollographql.apollo3.api.Optional
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.FriendSuggestQuery


/**
 * UI State that represents FriendScreen
 **/
data class FriendSuggestUiState(
    val friendSuggestState: FriendSuggestState,
)

data class FriendSuggestState(
//    val friendRequests: FriendRequests = FriendRequests::class.java.getConstructor().newInstance(),
    val friendSuggests: List<FriendSuggestQuery.Node>? = null,
    var isLoading: Boolean = false,
    val error: String? = null,
    val after: Optional<String?> = Optional.Absent,
    val endReached: Boolean = false
)


/**
 * Friend Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class FriendSuggestActions(
    val onClick: () -> Unit = {},
    val onDeleteSuggest: (String) -> Unit = {},
    val onSendFriendRequest: (String) -> Unit = {},
    val onScroll: () -> Unit = {}
)
