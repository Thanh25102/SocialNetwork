package tech.mobile.social.presentation.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.formatTimeAgo
import java.time.LocalDateTime

@Composable
fun PostComponent(
    avatarResource: Int,
    authorName: String,
    postTime: LocalDateTime,
    content: String,
    imageResource: Int? = null
) {
    Column(modifier = Modifier
        .padding(16.dp)
        .shadow(
            elevation = 4.dp,
            shape = RectangleShape,
            clip = true
        )
        .background(
            color = Color.White,
            shape = RectangleShape
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = avatarResource),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = authorName,
                )
                Text(text = formatTimeAgo(LocalDateTime.now()))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = content)

        Spacer(modifier = Modifier.height(8.dp))

        imageResource?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "Post Image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* handle click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.favorite_24),
                    contentDescription = "Favorite"
                )
            }
            Text(text = "1k thích")

            IconButton(onClick = { /* handle click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.comment_24),
                    contentDescription = "Comment"
                )
            }
            Text(text = "100 Bình luận")

            IconButton(onClick = { /* handle click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.share_24),
                    contentDescription = "Share"
                )
            }
            Text(text = "Chia sẻ")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PostComponent(
        R.drawable.manhthanh_3x4,
        "Thành",
        LocalDateTime.now(),
        "nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
        R.drawable.img
    )
}