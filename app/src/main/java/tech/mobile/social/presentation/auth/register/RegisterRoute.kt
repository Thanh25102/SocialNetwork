package tech.mobile.social.presentation.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun RegisterRoute(
    navController: NavController,
    coordinator: RegisterCoordinator = rememberRegisterCoordinator(navController = navController)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(RegisterState())

    // UI Actions
    val actions = rememberRegisterActions(coordinator)

    // UI Rendering
    RegisterScreen(uiState, actions)
}


@Composable
fun rememberRegisterActions(coordinator: RegisterCoordinator): RegisterActions {
    return remember(coordinator) {
        RegisterActions(
            onRegister = coordinator::onRegister,
            onNameChange = coordinator.viewModel::doNameChange,
            onEmailChange = coordinator.viewModel::doEmailChange,
            onPasswordChange = coordinator.viewModel::doPasswordChange,
            onPasswordConfirmChange = coordinator.viewModel::doPasswordConfirmChange,
            goBack = coordinator::goBack
        )
    }
}