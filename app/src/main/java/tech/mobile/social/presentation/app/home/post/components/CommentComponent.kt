package tech.mobile.social.presentation.app.home.post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.formatTimeAgo
import tech.mobile.social.ui.theme.HiddenTextColor
import java.time.LocalDateTime

@Composable
fun CommentComponent(
    modifier: Modifier = Modifier,
    avatarResource: Int, name: String, time: LocalDateTime, content: String
) {
    Row(modifier) {
        Image(
            painter = painterResource(id = avatarResource),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            // make text bold
            Column(
                modifier = Modifier
                    .background(color = HiddenTextColor.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                    .wrapContentWidth(Alignment.Start)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 2.dp)
                )
                Text(
                    text = content, fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 2.dp)
                )
            }
            Row(modifier = Modifier.padding(start = 8.dp, top = 2.dp)) {
                Text(text = formatTimeAgo(time), color = HiddenTextColor, fontSize = 8.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Thích", color = HiddenTextColor, fontSize = 8.sp)
            }
        }
    }
}

@Preview(name = "CommentComponent")
@Composable
private fun PreviewCommentComponent() {
    CommentComponent(
        Modifier,
        R.drawable.manhthanh_3x4,
        "Manh Thanh",
        LocalDateTime.now(),
        "Beat gốc nghe như đấm vào tai"
    )
}