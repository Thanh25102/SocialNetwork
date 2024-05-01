package tech.mobile.social.domain.model.friend

import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.type.RequestStatus
import java.time.LocalDateTime

//data class FriendRequest(
//    val id: Int,
//    val avatarResource: Int,
//    val name: String,
////    val time: LocalDateTime,
//    var isAccepted: Boolean = false
//)
//enum class RequestStatus {
//    ACCEPTED,
//    PENDING,
//    REJECTED,
//    SELF_REJECTED
//}


data class User(val id: String, val username: String)

data class PageInfo(val endCursor: String, val hasNextPage: Boolean)
data class FriendRequests(val edges: List<FriendRequestQuery.Edge>, val pageInfo: FriendRequestQuery.PageInfo)
data class FriendRequest(val id: String?, val receiver: HandleRequestMutation.Receiver?, val sender: HandleRequestMutation.Sender?, val status: RequestStatus?, val createdAt: LocalDateTime?, val updatedAt: LocalDateTime?, val deletedAt: LocalDateTime?)