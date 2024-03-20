package tech.mobile.social.presentation.app.home.group

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GroupScreen(
    state: GroupState,
    actions: GroupActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "Group")
private fun GroupScreenPreview() {
    GroupScreen(
        state = GroupState(),
        actions = GroupActions()
    )
}

