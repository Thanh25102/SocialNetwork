package tech.mobile.social.presentation.app.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.presentation.app.notifications.components.NotificationItemComponent
import java.time.LocalDateTime

import tech.mobile.social.R

@Composable
fun NotificationsScreen(
    state: NotificationsState,
    actions: NotificationsActions,
) {
    Column() {
        Text(
            text = "Thông báo", fontWeight = FontWeight.Bold, fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            items(10) {
                NotificationItemComponent(
                    avatarResource = R.drawable.manhthanh_3x4,
                    name = "Manh Thanh",
                    time = LocalDateTime.now(),
                    content = "Đã thích bài viết của bạn"
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
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

