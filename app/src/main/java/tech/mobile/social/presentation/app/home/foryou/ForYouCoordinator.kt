package tech.mobile.social.presentation.app.home.foryou

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import tech.mobile.social.shared.UserViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ForYouCoordinator(
    val forYouViewModel: ForYouViewModel,
    val userViewModel: UserViewModel
) {
    val forYouStateFlow = forYouViewModel.stateFlow
    val userStateFlow = userViewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberForYouCoordinator(
    forYouViewModel: ForYouViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
): ForYouCoordinator {
    return remember(forYouViewModel) {
        ForYouCoordinator(
            forYouViewModel = forYouViewModel,
            userViewModel = userViewModel
        )
    }
}