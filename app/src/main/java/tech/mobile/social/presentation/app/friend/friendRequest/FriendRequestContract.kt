package tech.mobile.social.presentation.app.friend.friendRequest

import androidx.collection.emptyObjectList
import kotlinx.coroutines.flow.emptyFlow
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.domain.model.friend.FriendRequests
import tech.mobile.social.domain.model.friend.User
import tech.mobile.social.presentation.app.home.post.PostState
import tech.mobile.social.type.RequestStatus
import java.time.LocalDateTime
import java.util.Optional


/**
 * UI State that represents FriendScreen
 **/
data class FriendRequestUiState(
    val friendRequestState: FriendRequestState,
)

data class FriendRequestState(
//    val friendRequests: FriendRequests = FriendRequests::class.java.getConstructor().newInstance(),
    val friendRequests: FriendRequests = FriendRequests(edges = emptyList(), pageInfo = FriendRequestQuery.PageInfo(endCursor = null, hasNextPage = false)),
    var isLoading: Boolean = false,
    val error: String = "",
)

data class FriendRequest(
//    val id: Int,
//    val avatarResource: Int,
//    val name: String,
//    var isAccepted: Boolean = false
    val id: String,
    val receiver: User,
    val sender: User,
    val status: RequestStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime
)

/**
 * Friend Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class FriendRequestActions(
    val onClick: () -> Unit = {},
    val onDeleteRequest: (FriendRequest) -> Unit = {},
    val onAcceptFriendRequest: (String) -> Unit = {}
)
