package tech.mobile.social.navigation.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import tech.mobile.social.Screens

data class BottomNavigationItem(
    val route: String = "",
    val title: String = "",
    val icon: ImageVector = Icons.Filled.Home
) {
    fun bottomNavigationItems() = listOf(
        BottomNavigationItem(Screens.Home.route, "Trang chủ", Icons.Filled.Home),
        BottomNavigationItem(Screens.Friends.route, "Bạn bè", Icons.Filled.Group),
        BottomNavigationItem(Screens.Post.route, "Đăng bài", Icons.Filled.AddCircleOutline),
        BottomNavigationItem(
            Screens.Notification.route,
            "Thông báo",
            Icons.Filled.Notifications
        ),
        BottomNavigationItem(Screens.Profile.route, "Cá nhân", Icons.Filled.AccountCircle)
    )

}