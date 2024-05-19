package tech.mobile.social.presentation.app.post.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.fragment.Posts
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsComponent(
    comments: Posts.Comments?,
    closeBottomSheet: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = { closeBottomSheet() },
        sheetState = sheetState,
        shape = RectangleShape,
        dragHandle = {
            Row(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = "like")
                    Text(text = "Phạm Lê Bảo Ân và 13 người khác")
                }
                Icon(Icons.Filled.Favorite, contentDescription = "like")
            }
        }
    ) {
        if (comments != null && comments.edges.isNotEmpty())
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(comments.edges.size) {
                    CommentComponent(
                        avatarResource = R.drawable.manhthanh_3x4,
                        name = comments.edges[it].node.user.username,
                        time = LocalDateTime.now(),
                        content = comments.edges[it].node.content
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        else
            Text(text = "Chưa có bình luận nào")
    }


}

@Preview(name = "CommentsComponent")
@Composable
private fun PreviewCommentsComponent() {
//    CommentsComponent()
}