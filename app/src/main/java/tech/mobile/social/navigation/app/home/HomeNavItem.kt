package tech.mobile.social.navigation.app.home

import tech.mobile.social.Screens

data class HomeNavItem ( val title:String="",val route:String="") {
    fun homeNavItems() = listOf(
        HomeNavItem("Dành cho bạn",Screens.ForYou.route),
        HomeNavItem("Nhóm",Screens.Group.route),
        HomeNavItem("Đang theo dõi",Screens.Following.route),
    )
}