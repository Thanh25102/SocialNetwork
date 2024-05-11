package tech.mobile.social.presentation.app.friend.friendRequest.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.FriendSuggestQuery
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.components.BtnApp
import tech.mobile.social.presentation.utils.formatTimeAgo
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.ui.theme.HiddenTextColor
import java.time.LocalDateTime

@Composable
fun FriendSuggestItemComponent(
//    avatarResource: Int, name: String, time: LocalDateTime,
    friendSuggest: FriendSuggestQuery.Node,
    onDelete: (String) -> Unit,
    onSendRequest: (String) -> Unit
) {
    val (isSentRequest, setIsSentRequest) = rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
        ,
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
            Text(text = friendSuggest.username, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Row() {
                if (!isSentRequest) {
//                    BtnApp(
//                        onClick = { onAccept(friendRequest.node.id) },
//                        label = "Chấp nhận",
//                        modifier = Modifier.width(120.dp)
//                    )
                Button(
                    onClick = {
                        setIsSentRequest(true);
                        onSendRequest(friendSuggest.id);
                    },
                    modifier = Modifier.width(240.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Kết bạn")
                }
                } else {
                    Button(
                        onClick = {
                            setIsSentRequest(false);
                            onDelete(friendSuggest.id)
                        },
                        modifier = Modifier.width(240.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Hủy")
                    }
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