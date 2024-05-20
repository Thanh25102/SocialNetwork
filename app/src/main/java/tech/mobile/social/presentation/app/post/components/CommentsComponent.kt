package tech.mobile.social.presentation.app.post.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ModeComment
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SendTimeExtension
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.fragment.Posts
import tech.mobile.social.presentation.utils.components.CommentApp
import tech.mobile.social.presentation.utils.components.InputApp
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsComponent(
    comments: Posts.Comments?,
    closeBottomSheet: () -> Unit = {}
) {
    var comment by rememberSaveable {
        mutableStateOf("")
    }
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
            Text(text = "Chưa có bình luận nào", modifier = Modifier.padding(vertical = 16.dp))

        Row(
            modifier = Modifier.padding(4.dp), // Padding around the whole row
            verticalAlignment = Alignment.CenterVertically // Align items vertically in the center
        ) {

            OutlinedTextField(
                value = comment,
                onValueChange = {
                    comment = it
                },
                modifier = Modifier
                    .weight(8f)
                    .padding(end = 8.dp) // Padding between the input and the button
                    .background(Color.White, shape = RoundedCornerShape(8.dp)), // Background color and rounded corners
                leadingIcon = {
                    Icon(Icons.Filled.ModeComment, contentDescription = null)
                },
                placeholder = {
                    Text("Enter comment")
                },
                singleLine = true,
            )
            Button(
                onClick = {
                    //send comment state "comment"
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF6200EE)), // Change button color
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 4.dp), // Padding inside button
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.SendTimeExtension,
                    contentDescription = "Send",
                    tint = Color.White // Icon color
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview(name = "CommentsComponent")
@Composable
private fun PreviewCommentsComponent() {
}