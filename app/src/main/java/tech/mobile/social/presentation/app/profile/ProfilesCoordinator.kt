package tech.mobile.social.presentation.app.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.mobile.social.Screens
import tech.mobile.social.presentation.app.friend.friendRequest.FriendRequestViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ProfilesCoordinator(
    val viewModel: ProfilesViewModel,
    val navController: NavController,
    val friendRequestViewModel: FriendRequestViewModel,
) {
    val screenStateFlow = viewModel.stateFlow

    fun doOpenFriend() {
        navController.navigate(Screens.Home.route)
    }
}

@Composable
fun rememberProfilesCoordinator(
    viewModel: ProfilesViewModel = hiltViewModel(),
    friendRequest: FriendRequestViewModel =hiltViewModel(),
    navController: NavController
): ProfilesCoordinator {
    return remember(viewModel) {
        ProfilesCoordinator(
            viewModel = viewModel,
            friendRequestViewModel= friendRequest,
            navController = navController
        )
    }
}