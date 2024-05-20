package tech.mobile.social.navigation.app.friend

import tech.mobile.social.Screens

data class FriendNavItem(val title: String = "", val route: String = "") {
    fun friendNavItems() = listOf(
        FriendNavItem("Lời mời kết bạn", Screens.FriendRequest.route),
        FriendNavItem("Gợi ý", Screens.Suggestion.route),
    )
}