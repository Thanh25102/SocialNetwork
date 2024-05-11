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
import tech.mobile.social.presentation.app.friend.friendRequest.components.FriendRequestItemComponent

@Composable
fun NotificationsScreen(
    state: NotificationsState,
    actions: NotificationsActions,
) {
    Column() {
//        Text(
//            text = "Thông báo", fontWeight = FontWeight.Bold, fontSize = 16.sp,
//            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
//        )
        Spacer(modifier = Modifier.height(20.dp))
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(4.dp)
//        ) {
//            items(10) {
//                NotificationItemComponent(
//                    notification
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//            }
//        }

        LazyColumn {
            state.notifications?.let { it ->
                items(it.size, key = { it }) { it ->
                    NotificationItemComponent(
                        notification = state.notifications[it],
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            item {

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

