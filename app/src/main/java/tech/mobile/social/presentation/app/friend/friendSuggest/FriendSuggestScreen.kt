package tech.mobile.social.presentation.app.friend.friendSuggest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            state.friendSuggests?.let { it ->
                items(it.size, key = { it }) { it ->
                    if(it >= state.friendSuggests.size - 1 && !state.endReached && !state.isLoading){

                    }
                    FriendSuggestItemComponent(
                        friendSuggest = state.friendSuggests[it],
                        onDelete = actions.onDeleteSuggest,
                        onSendRequest = actions.onSendFriendRequest
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            item {
                if(state.isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "FriendSuggest")
private fun FriendScreenPreview() {
    FriendRequestScreen(
        state = FriendSuggestState(),
        actions = FriendSuggestActions(),

    )
}

