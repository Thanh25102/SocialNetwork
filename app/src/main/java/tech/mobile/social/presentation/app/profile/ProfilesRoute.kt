package tech.mobile.social.presentation.app.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun ProfilesRoute(
    navController: NavController,
    coordinator: ProfilesCoordinator = rememberProfilesCoordinator(navController = navController)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState()

    // UI Actions
    val actions = rememberProfilesActions(coordinator)

    // UI Rendering
    ProfilesScreen(uiState, actions)
}


@Composable
fun rememberProfilesActions(coordinator: ProfilesCoordinator): ProfilesActions {
    return remember(coordinator) {
        ProfilesActions(
            onClick = coordinator::doOpenFriend,
            onopenFriend = coordinator.viewModel::onopenFriend
        )
    }
}