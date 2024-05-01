package tech.mobile.social.presentation.app.friend.friendRequest.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.R
import tech.mobile.social.presentation.app.friend.friendRequest.FriendRequest
import tech.mobile.social.presentation.utils.components.BtnApp
import tech.mobile.social.presentation.utils.formatTimeAgo
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.ui.theme.HiddenTextColor
import java.time.LocalDateTime

@Composable
fun FriendRequestItemComponent(
//    avatarResource: Int, name: String, time: LocalDateTime,
    friendRequest: FriendRequestQuery.Edge,
    onDelete: (FriendRequestQuery.Edge) -> Unit,
    onAccept: (String) -> Unit
) {
//    val (isAccepted, setIsAccepted) = rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.manhthanh_3x4),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            // make text bold
            Text(text = "Phú Thịnh", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = formatTimeAgo(LocalDateTime.now()), color = HiddenTextColor, fontSize = 12.sp)
            Row() {
                if (friendRequest.node.status != RequestStatus.ACCEPTED) {
                    BtnApp(
                        onClick = { onAccept(friendRequest.node.id) },
                        label = "Chấp nhận",
                        modifier = Modifier.width(120.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    BtnApp(
                        onClick = { onDelete(friendRequest) },
                        label = "Xóa",
                        outline = true,
                        modifier = Modifier.width(120.dp)
                    )
                } else {
                    Text(text="Đã chấp nhận lời mời kết bạn", fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.width(240.dp))
                }
            }
        }
    }
}

//@Preview(name = "FriendItemComponent")
//@Composable
//private fun PreviewFriendItemComponent() {
//    FriendRequestItemComponent(R.drawable.manhthanh_3x4, "Manh Thanh", LocalDateTime.now())
////    FriendRequestItemComponent()
//}

//@Composable
//fun FriendRequestItemComponent(
//    friendRequest: FriendRequestQuery.Edge,
//    onDelete: (FriendRequest) -> Unit,
//    onAccept: (String)  -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(R.drawable.manhthanh_3x4),
//            contentDescription = "Avatar",
//            modifier = Modifier
//                .size(80.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Column {
//            // make text bold
//
//            Text(text = friendRequest.node.receiver.username, fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            Text(text = formatTimeAgo(LocalDateTime.now()), color = HiddenTextColor, fontSize = 12.sp)
//            Row {
//                BtnApp(onClick = {}, label = "Chấp nhận", modifier = Modifier.width(120.dp))
//                Spacer(modifier = Modifier.width(4.dp))
//                BtnApp(onClick = {}, label = "Xóa", outline = true, modifier = Modifier.width(120.dp))
//            }
//        }
//    }
//}

//@Preview(name = "FriendItemComponent")
//@Composable
//private fun PreviewFriendItemComponent() {
//    FriendItemComponent(R.drawable.manhthanh_3x4, "Manh Thanh", LocalDateTime.now())
//}