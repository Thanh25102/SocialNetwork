package tech.mobile.social.presentation.app.home.foryou

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.combine
import tech.mobile.social.shared.UserState


@Composable
fun ForYouRoute(
    coordinator: ForYouCoordinator = rememberForYouCoordinator()
) {
    // State observing and declarations
    val uiState by combine(
        coordinator.forYouStateFlow,
        coordinator.userStateFlow,
    ) { forYouState,  userState ->
        ForYouUiState(
            forYouState = forYouState,
            userState = userState
        )
    }
        .collectAsState(
            initial = ForYouUiState(
                ForYouState(emptyList()),UserState(null, false)
            )
        )

    // UI Actions
    val actions = rememberForYouActions(coordinator)

    // UI Rendering
    ForYouScreen(uiState, actions)
}


@Composable
fun rememberForYouActions(coordinator: ForYouCoordinator): ForYouActions {
    return remember(coordinator) {
        ForYouActions(
            onClick = coordinator::doStuff,
            onScroll = coordinator.forYouViewModel::loadNextItems
        )
    }
}