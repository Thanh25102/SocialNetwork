package tech.mobile.social.presentation.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import kotlinx.coroutines.flow.combine
import tech.mobile.social.shared.UserState


data class CombinedUiState(
    val loginState: LoginState,
    val userState: UserState
)

@Composable
fun LoginRoute(
    navController: NavController,
    coordinator: LoginCoordinator = rememberLoginCoordinator(navController = navController)
) {
    // State observing and declarations
    val uiState by combine(
        coordinator.loginStateFlow,
        coordinator.userStateFlow
    ) { loginState, userState -> CombinedUiState(loginState, userState) }
        .collectAsState(initial = CombinedUiState(LoginState(), UserState()))

    // UI Actions
    val actions = rememberLogin2Actions(coordinator)

    // UI Rendering
    LoginScreen(uiState, actions)

}



@Composable
fun rememberLogin2Actions(coordinator: LoginCoordinator): LoginActions {
    return remember(coordinator) {
        LoginActions(
            onLogin = coordinator::doLogin,
            onPasswordChange = coordinator.loginViewModel::updatePassword,
            onEmailChange = coordinator.loginViewModel::updateEmail,
            navRegister = coordinator::navRegister
        )
    }
}