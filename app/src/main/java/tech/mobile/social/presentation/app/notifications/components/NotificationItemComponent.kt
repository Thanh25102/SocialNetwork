package tech.mobile.social.presentation.app.notifications.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.NotificationsQuery
import tech.mobile.social.presentation.utils.formatTimeAgo
import java.time.LocalDateTime
import tech.mobile.social.R
import tech.mobile.social.presentation.app.notifications.Notification
import tech.mobile.social.type.NotificationType
import tech.mobile.social.ui.theme.HiddenTextColor

@Composable
fun NotificationItemComponent(
//    avatarResource: Int, name: String, time: LocalDateTime,content:String,
    notification: Notification,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.manhthanh_3x4),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            // make text bold
            notification?.senderNotification?.sender?.username.let {
                if (it != null) {
                    Text(text = it, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
            val content: String = when(notification?.type) {
                NotificationType.FRIEND_REQUEST -> "đã gửi lời mời kết bạn"
                NotificationType.COMMENT -> "đã bình luận bài viết của bạn"
                NotificationType.LIKE -> "đã thích bài viết của bạn"
                NotificationType.POST -> "đã đăng một bài viết mới"
                NotificationType.FRIEND_REQUEST_ACCEPTED -> "đã chấp nhận lời mời kết bạn"
                else -> {
                    ""
                }
            }
            Text(text = content, fontWeight = FontWeight.Normal, fontSize = 16.sp)
            Text(text = formatTimeAgo(LocalDateTime.now()), color = HiddenTextColor, fontSize = 12.sp)
        }
    }
}

//@Preview(name = "NotificationItemComponent")
//@Composable
//private fun PreviewNotificationItemComponent() {
//    NotificationItemComponent(R.drawable.manhthanh_3x4, "Manh Thanh", LocalDateTime.now(),"Đã thích bài viết của bạn")
//}