package tech.mobile.social.presentation.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.mobile.social.Screens

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class RegisterCoordinator(
    val viewModel: RegisterViewModel,
    val navController: NavController
) {
    val screenStateFlow = viewModel.stateFlow

    fun onRegister() {
        viewModel.doRegister()
        navController.navigate(Screens.Login.route)
        // TODO Handle UI Action
    }
}

@Composable
fun rememberRegisterCoordinator(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController
): RegisterCoordinator {
    return remember(viewModel) {
        RegisterCoordinator(
            viewModel = viewModel,
            navController = navController
        )
    }
}