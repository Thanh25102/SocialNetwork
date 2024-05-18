package tech.mobile.social.presentation.app.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.combine
import tech.mobile.social.shared.UserState


@Composable
fun HomeRoute(
    coordinator: HomeCoordinator = rememberHomeCoordinator()
) {
    // State observing and declarations
    val uiState by combine(
        coordinator.forYouStateFlow,
        coordinator.userStateFlow,
    ) { homeState, userState ->
        HomeUiState(
            homeState = homeState,
            userState = userState
        )
    }
        .collectAsState(
            initial = HomeUiState(
                HomeState(emptyList()), UserState(null, false)
            )
        )

    // UI Actions
    val actions = rememberHomeActions(coordinator)

    // UI Rendering
    HomeScreen(uiState, actions)
}


@Composable
fun rememberHomeActions(coordinator: HomeCoordinator): HomeActions {
    return remember(coordinator) {
        HomeActions(
            onClick = coordinator::doStuff,
            onScroll = coordinator.homeViewModel::loadNextItems
        )
    }
}