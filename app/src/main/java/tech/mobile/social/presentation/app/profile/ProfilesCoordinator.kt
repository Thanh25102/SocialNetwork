package tech.mobile.social.presentation.app.profile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.mobile.social.Screens
import tech.mobile.social.presentation.app.friend.friendRequest.FriendRequestViewModel
import tech.mobile.social.shared.UserViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ProfilesCoordinator(
    val userViewModel: UserViewModel,
    val profileModel: ProfilesViewModel,
    val navController: NavController,
    val friendRequestViewModel: FriendRequestViewModel,
) {
    val screenStateFlow = profileModel.stateFlow

    fun doOpenFriend() {
        navController.navigate(Screens.Home.route)
    }

    fun doLogout() {
        userViewModel.doLogout()
        navController.popBackStack(Screens.Login.route, false, false)
    }

}

@Composable
fun rememberProfilesCoordinator(
    userViewModel: UserViewModel = hiltViewModel(),
    profileModel: ProfilesViewModel = hiltViewModel(),
    friendRequest: FriendRequestViewModel = hiltViewModel(),
    navController: NavController
): ProfilesCoordinator {
    return remember(profileModel) {
        ProfilesCoordinator(
            userViewModel = userViewModel,
            profileModel = profileModel,
            friendRequestViewModel = friendRequest,
            navController = navController
        )
    }
}