package tech.mobile.social.presentation.app.friend.friendSuggest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tech.mobile.social.presentation.app.friend.friendRequest.components.FriendRequestItemComponent
import tech.mobile.social.presentation.app.friend.friendRequest.components.FriendSuggestItemComponent

@Composable
fun FriendRequestScreen(
    state: FriendSuggestState,
    actions: FriendSuggestActions,
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
            state.friendSuggests?.edges?.let { it ->
                items(it.size, key = { it }) { it ->
                    FriendSuggestItemComponent(
                        friendSuggest = state.friendSuggests.edges[it],
                        onDelete = actions.onDeleteSuggest,
                        onSendRequest = actions.onSendFriendRequest
                    )
                }
            }
            item {

            }
        }
    }
}

@Composable
@Preview(name = "FriendSuggest")
private fun FriendScreenPreview() {
    FriendRequestScreen(
        state = FriendSuggestState(),
        actions = FriendSuggestActions()
    )
}

