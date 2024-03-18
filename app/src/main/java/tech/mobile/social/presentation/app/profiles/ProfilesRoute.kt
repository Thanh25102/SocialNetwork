package tech.mobile.social.presentation.app.profiles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun ProfilesRoute(
    coordinator: ProfilesCoordinator = rememberProfilesCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(ProfilesState())

    // UI Actions
    val actions = rememberProfilesActions(coordinator)

    // UI Rendering
    ProfilesScreen(uiState, actions)
}


@Composable
fun rememberProfilesActions(coordinator: ProfilesCoordinator): ProfilesActions {
    return remember(coordinator) {
        ProfilesActions(
            onClick = coordinator::doStuff
        )
    }
}