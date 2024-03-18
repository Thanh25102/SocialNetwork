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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.presentation.utils.components.BtnApp
import tech.mobile.social.presentation.utils.formatTimeAgo
import java.time.LocalDateTime
import tech.mobile.social.R
import tech.mobile.social.presentation.app.friend.components.FriendItemComponent

@Composable
fun NotificationItemComponent(
    avatarResource: Int, name: String, time: LocalDateTime,content:String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.manhthanh_3x4 ),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(text = name)
            Text(text = content)
            Text(text = formatTimeAgo(LocalDateTime.now()))
        }
    }
}

@Preview(name = "NotificationItemComponent")
@Composable
private fun PreviewNotificationItemComponent() {
    NotificationItemComponent(R.drawable.manhthanh_3x4, "Manh Thanh", LocalDateTime.now(),"Đã thích bài viết của bạn")
}