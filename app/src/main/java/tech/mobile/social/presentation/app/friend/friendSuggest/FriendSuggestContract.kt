package tech.mobile.social.presentation.app.friend.friendSuggest

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
    val friendSuggests: FriendSuggestQuery.Suggests? = null,
    var isLoading: Boolean = false,
    val error: String = "",
)


/**
 * Friend Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class FriendSuggestActions(
    val onClick: () -> Unit = {},
    val onDeleteSuggest: (String) -> Unit = {},
    val onSendFriendRequest: (String) -> Unit = {}
)
