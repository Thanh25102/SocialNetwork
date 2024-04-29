package tech.mobile.social.navigation.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tech.mobile.social.Screens
import tech.mobile.social.navigation.app.friend.FriendNavBar
import tech.mobile.social.navigation.app.home.HomeNavBar
import tech.mobile.social.presentation.app.notifications.NotificationsRoute
import tech.mobile.social.presentation.app.posts.PostsRoute
import tech.mobile.social.presentation.app.profile.ProfilesRoute

@Composable
fun BottomNavigationBar(rootNavController: NavController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = if (currentDestination?.route != Screens.Post.route) {
            {
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
        } else {
            {}
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues),

            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(300)
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(300)
                )
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(300)
                )
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(300)
                )
            }
        ) {
            composable(Screens.Home.route) {
                HomeNavBar(navController)
            }
            composable(Screens.Friends.route) {
//                FriendsScreen(navController)
//                FriendRoute()
                FriendNavBar(navController)
            }
            composable(Screens.Post.route) {
//                PostScreen(navController)
                PostsRoute()
            }
            composable(Screens.Notification.route) {
//                NotificationScreen(navController)
                NotificationsRoute()
            }
            composable(Screens.Profile.route) {
//                ProfileScreen(navController)
                ProfilesRoute()
            }
        }
    }
}