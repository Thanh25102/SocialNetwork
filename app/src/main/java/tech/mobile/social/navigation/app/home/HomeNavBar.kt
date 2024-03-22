package tech.mobile.social.navigation.app.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tech.mobile.social.Screens
import tech.mobile.social.presentation.app.home.foryou.ForYouRoute

@Composable
fun HomeNavBar(appNavController: NavController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            NavigationBar(
                containerColor = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HomeNavItem().homeNavItems().forEachIndexed { _, navigationItem ->
                    Column(
                        modifier = Modifier

                            .weight(1f)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = navigationItem.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            fontWeight = if (currentDestination?.route == navigationItem.route) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                        if (currentDestination?.route == navigationItem.route) {
                            HorizontalDivider(
                                thickness = 3.dp, modifier = Modifier
                                    .width(44.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
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
