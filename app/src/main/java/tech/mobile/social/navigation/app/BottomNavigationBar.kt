package tech.mobile.social.navigation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import tech.mobile.social.Screens
import tech.mobile.social.navigation.app.home.HomeNavBar
import tech.mobile.social.screens.app.FriendsScreen
import tech.mobile.social.screens.app.NotificationScreen
import tech.mobile.social.screens.app.PostScreen
import tech.mobile.social.screens.app.ProfileScreen

@Composable
fun BottomNavigationBar(rootNavController:NavController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                    NavigationBarItem(
                        selected = navigationItem.route == currentDestination?.route,
//                        label = {
//                            Text(navigationItem.title)
//                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.title
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.Home.route) {
                HomeNavBar(navController)
            }

            composable(Screens.Friends.route) {
                FriendsScreen(navController)
            }
            composable(Screens.Post.route) {
                PostScreen(navController)
            }
            composable(Screens.Notification.route) {
                NotificationScreen(navController)
            }
            composable(Screens.Profile.route) {
                ProfileScreen(navController)
            }
        }
    }
}