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
        coordinator.paginationStateFlow,
        coordinator.isRefreshFlow,
        coordinator.userStateFlow,
    ) { forYouState, paginationState, isRefreshState, userState ->
        ForYouUiState(
            forYouState = forYouState,
            pagingState = paginationState,
            refreshState = isRefreshState,
            userState = userState
        )
    }
        .collectAsState(
            initial = ForYouUiState(
                ForYouState(emptyList()), PagingState(), false, UserState(null, false)
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
            onScroll = coordinator.forYouViewModel::getPostsPaginated
        )
    }
}