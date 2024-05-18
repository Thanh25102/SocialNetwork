package tech.mobile.social.presentation.app.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import tech.mobile.social.shared.UserViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class HomeCoordinator(
    val homeViewModel: HomeViewModel,
    val userViewModel: UserViewModel
) {
    val forYouStateFlow = homeViewModel.stateFlow
    val userStateFlow = userViewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberHomeCoordinator(
    homeViewModel: HomeViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
): HomeCoordinator {
    return remember(homeViewModel) {
        HomeCoordinator(
            homeViewModel = homeViewModel,
            userViewModel = userViewModel
        )
    }
}