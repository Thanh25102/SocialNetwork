package tech.mobile.social.data.mapper

import tech.mobile.social.AuthorizeMutation
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.domain.model.auth.Auth
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.model.friend.FriendRequest
import tech.mobile.social.domain.model.friend.FriendRequests

fun FriendRequestQuery.Data.toFriendRequest(): FriendRequests {
    return FriendRequests(
        edges = get_incoming_requests.edges,
        pageInfo = get_incoming_requests.pageInfo
    )
}