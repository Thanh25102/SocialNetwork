package tech.mobile.social.data.mapper

import tech.mobile.social.AuthorizeMutation
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.model.friend.FriendRequest
import tech.mobile.social.domain.model.friend.FriendRequests

fun FriendRequestQuery.Data.toFriendRequests(): FriendRequests {
    return FriendRequests(
        edges = get_incoming_requests.edges,
        pageInfo = get_incoming_requests.pageInfo
    )
}

fun HandleRequestMutation.Data.toFriendRequest(): FriendRequest {
    return FriendRequest (
        id = handle_request?.id ?: "",
        receiver = handle_request?.receiver,
        sender = handle_request?.sender,
        status = handle_request?.status,
        createdAt = handle_request?.createdAt,
        updatedAt = handle_request?.updatedAt,
        deletedAt = handle_request?.deletedAt
    )
}