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
        BottomNavigationItem(Screens.Home.route, "Home", Icons.Filled.Home),
        BottomNavigationItem(Screens.Friends.route, "Friends", Icons.Filled.Group),
        BottomNavigationItem(Screens.Post.route, "Post", Icons.Filled.PostAdd),
        BottomNavigationItem(Screens.Notification.route, "Notification", Icons.Filled.Notifications),
        BottomNavigationItem(Screens.Profile.route, "Profile", Icons.Filled.AccountCircle)
    )

}