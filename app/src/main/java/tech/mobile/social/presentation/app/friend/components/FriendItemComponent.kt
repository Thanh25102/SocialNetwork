package tech.mobile.social.presentation.app.friend.components

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
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.components.BtnApp
import tech.mobile.social.presentation.utils.formatTimeAgo
import java.time.LocalDateTime

@Composable
fun FriendItemComponent(
    avatarResource: Int, name: String, time: LocalDateTime
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = avatarResource),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(text = name)
            Text(text = formatTimeAgo(LocalDateTime.now()))
            Row {
                BtnApp(onClick = {}, label = "Chấp nhận")
                BtnApp(onClick = {}, label = "Xóa")
            }
        }
    }
}

@Preview(name = "FriendItemComponent")
@Composable
private fun PreviewFriendItemComponent() {
    FriendItemComponent(R.drawable.manhthanh_3x4, "Manh Thanh", LocalDateTime.now())
}