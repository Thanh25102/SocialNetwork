package tech.mobile.social.presentation.app.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import tech.mobile.social.R.drawable
import tech.mobile.social.presentation.app.post.components.CommentsComponent
import tech.mobile.social.presentation.utils.formatTimeAgo
import java.time.LocalDateTime

@Composable
fun PostScreen(
    state: State,
    actions: PostActions,
) {
    val (
        likes, isSheetOpen, isLiked, friends, commentsCount, comments, post
    ) = state

    val painter = rememberCoilPainter("http://171.239.144.144:8334/" + post?.image)

    if (post !== null) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = post.avatarResource ?: drawable.manhthanh_3x4),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = post.authorName,
                    )
                    Text(text = formatTimeAgo(LocalDateTime.now()))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = post.content)

            Spacer(modifier = Modifier.height(8.dp))


            post.image?.let {
                Image(
                    painter = painter,
                    contentDescription = "Post Image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            HorizontalDivider(
                color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = drawable.favorite_24),
                        contentDescription = "Favorite"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${post.likes}")
                }

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { actions.onOpenComments() }) {
                    Icon(
                        painter = painterResource(id = drawable.comment_24),
                        contentDescription = "Comment"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${post.commentsCount} Bình luận")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = drawable.share_24),
                        contentDescription = "Share"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Chia sẻ")
                }
            }
        }
        if (isSheetOpen && comments != null) {
            CommentsComponent(comments = post.comments, closeBottomSheet = actions.onCloseComments)
        }
    } else {
        Text(text = "Đã xảy ra lỗi khi cố gắng hiển thị bài viết. Vui lòng thử lại sau.")
    }
}

@Composable
@Preview(name = "Post")
private fun PostScreenPreview() {
//    PostScreen(
//        state = State(
//            likes = 0,
//            isLiked = false,
//            friends = emptyList(),
//            commentsCount = 0,
//            comments = emptyList(),
//            post = PostState(
//                0, 0,
//                "",
//                drawable.manhthanh_3x4,
//                "Thành",
//                Date(),
//                "nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
//                drawable.img,
//                false
//            ),
//        ), actions = PostActions()
//    )
}
