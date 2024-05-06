package tech.mobile.social.presentation.app.friend.friendRequest

import tech.mobile.social.FriendRequestQuery


/**
 * UI State that represents FriendScreen
 **/
data class FriendRequestUiState(
    val friendRequestState: FriendRequestState,
)

data class FriendRequestState(
//    val friendRequests: FriendRequests = FriendRequests::class.java.getConstructor().newInstance(),
    val friendRequests: List<FriendRequestQuery.Node>? = null,
    var isLoading: Boolean = false,
    val error: String = "",
)


/**
 * Friend Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class FriendRequestActions(
    val onClick: () -> Unit = {},
    val onDeleteRequest: (FriendRequestQuery.Node) -> Unit = {},
    val onAcceptFriendRequest: (FriendRequestQuery.Node) -> Unit = {}
)
