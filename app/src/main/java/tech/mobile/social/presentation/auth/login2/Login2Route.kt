package tech.mobile.social.presentation.auth.login2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun Login2Route(
    navController: NavController,
    coordinator: Login2Coordinator = rememberLogin2Coordinator(navController = navController)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(Login2State())

    // UI Actions
    val actions = rememberLogin2Actions(coordinator)

    // UI Rendering
    Login2Screen(uiState, actions)
}


@Composable
fun rememberLogin2Actions(coordinator: Login2Coordinator): Login2Actions {
    return remember(coordinator) {
        Login2Actions(
            onLogin = coordinator::doLogin,
            onPasswordChange = coordinator.viewModel::updatePassword,
            onEmailChange = coordinator.viewModel::updateEmail,
            navRegister = coordinator::navRegister
        )
    }
}