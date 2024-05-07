package tech.mobile.social.presentation.app.friend.friendRequest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.R
import tech.mobile.social.presentation.app.friend.friendRequest.components.FriendRequestItemComponent
import java.time.LocalDateTime

@Composable
fun FriendRequestScreen(
    state: FriendRequestState,
    actions: FriendRequestActions,
) {
    Column {
//        Text(
//            text = "Danh sách bạn bè", fontWeight = FontWeight.Bold, fontSize = 16.sp,
//            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(4.dp)
//        ) {
//            items(1) {
//                FriendRequestItemComponent(
//                    avatarResource = R.drawable.manhthanh_3x4,
//                    name = "Manh Thanh",
//                    time = LocalDateTime.now()
//                )
//            }
//        }

//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(4.dp)
//        ) {
//            var i = 0;
//            items(state.friendRequests, key = {it.id}) {request ->
//                i += 1;
//                FriendRequestItemComponent(
//                    friendRequest = request,
//                    onDelete = actions.onDeleteRequest,
//                    onAccept = actions.onAcceptFriendRequest
//                )
//            }
//        }

        LazyColumn {
            state.friendRequests?.let { it ->
                items(it.size, key = { it }) { it ->
                    FriendRequestItemComponent(
                        friendRequest = state.friendRequests[it],
                        onDelete = actions.onDeleteRequest,
                        onAccept = actions.onAcceptFriendRequest
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            item {

            }
        }
    }
}

@Composable
@Preview(name = "FriendRequest")
private fun FriendScreenPreview() {
    FriendRequestScreen(
        state = FriendRequestState(),
        actions = FriendRequestActions()
    )
}

