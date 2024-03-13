package tech.mobile.social

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String = "",
    val title: String = "",
    val icon: ImageVector = Icons.Filled.Home
) {
    fun bottomNavigationItems() = listOf(
        BottomNavigationItem(Screens.Home.route, "Home", Icons.Filled.Home),
        BottomNavigationItem(Screens.Search.route, "Search", Icons.Filled.Search),
        BottomNavigationItem(Screens.Profile.route, "Profile", Icons.Filled.AccountCircle)
    )

}