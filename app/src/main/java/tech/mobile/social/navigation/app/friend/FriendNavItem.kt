package tech.mobile.social.navigation.app.friend

import tech.mobile.social.Screens
import tech.mobile.social.navigation.app.home.HomeNavItem

data class FriendNavItem ( val title:String="",val route:String="") {
    fun friendNavItems() = listOf(
        HomeNavItem("Lời mời kết bạn", Screens.FriendRequest.route),
        HomeNavItem("Gợi ý", Screens.Suggestion.route),
    )
}