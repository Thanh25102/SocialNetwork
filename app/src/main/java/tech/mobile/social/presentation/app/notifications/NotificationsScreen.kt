package tech.mobile.social.presentation.app.notifications

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.presentation.app.notifications.components.NotificationItemComponent
import java.time.LocalDateTime

@Composable
fun NotificationsScreen(
    state: NotificationsState,
    actions: NotificationsActions,
) {
    LazyColumn() {
        items(10) {
            NotificationItemComponent(
                avatarResource = 0,
                name = "Manh Thanh",
                time = LocalDateTime.now(),
                content = "Đã thích bài viết của bạn"
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview(name = "Notifications")
private fun NotificationsScreenPreview() {
    NotificationsScreen(
        state = NotificationsState(),
        actions = NotificationsActions()
    )
}

