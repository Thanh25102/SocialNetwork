package tech.mobile.social.presentation.app.home.flowing

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FlowingScreen(
    state: FlowingState,
    actions: FlowingActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "Flowing")
private fun FlowingScreenPreview() {
    FlowingScreen(
        state = FlowingState(),
        actions = FlowingActions()
    )
}

