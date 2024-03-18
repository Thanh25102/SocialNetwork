package tech.mobile.social.presentation.test

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TestScreen(
    state: TestState,
    actions: TestActions,
) {
    // TODO UI Rendering
    Text(text = "Hellworld")
}

@Composable
@Preview(name = "Test")
private fun TestScreenPreview() {
    TestScreen(
        state = TestState(),
        actions = TestActions()
    )
}

