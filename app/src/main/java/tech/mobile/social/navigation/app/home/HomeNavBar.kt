package tech.mobile.social.navigation.app.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import tech.mobile.social.presentation.app.home2.foryou.ForYouRoute

@Composable
fun HomeNavBar(appNavController: NavController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NavigationBar {
                HomeNavItem().homeNavItems().forEachIndexed { _, navigationItem ->
                    Button(onClick = {
                        navController.navigate(navigationItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }) {
                        Text(text = navigationItem.title)
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.ForYou.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.ForYou.route) {
//                ForYouScreen(navController)
                ForYouRoute()
            }
            composable(Screens.Group.route) {
//                GroupScreen(navController)
            }
            composable(Screens.Following.route) {
//                FlowingScreen(navController)
            }
        }
    }
}