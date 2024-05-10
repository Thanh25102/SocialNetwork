package tech.mobile.social.presentation.app.friend.friendSuggest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.R
import tech.mobile.social.presentation.app.friend.friendRequest.components.FriendRequestItemComponent
import tech.mobile.social.presentation.app.friend.friendRequest.components.FriendSuggestItemComponent
import tech.mobile.social.presentation.app.home.foryou.components.InfiniteListHandler

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
        val lazyListState = rememberLazyListState()
        val lottieComp by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
        val lottieProgress by animateLottieCompositionAsState(
            composition = lottieComp,
            iterations = LottieConstants.IterateForever,
        )
        val myState = state.friendSuggests
        LazyColumn(state = lazyListState) {
            state.friendSuggests?.let { it ->
                items(it.size, key = { it }) { it ->
                    if(state.after != Optional.Absent && it >= state.friendSuggests.size - 1 && !state.endReached && !state.isLoading){
                        actions.onScroll();
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

//        InfiniteListHandler(lazyListState = lazyListState) {
//            actions.onScroll()
//        }
//        when {
//            state.isLoading -> {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    LottieAnimation(
//                        composition = lottieComp,
//                        progress = { lottieProgress },
//                    )
//                }
//            }
//
//            state.error?.isNotEmpty() == true -> {
//                Text(
//                    text = state.error,
//                    color = MaterialTheme.colorScheme.error,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp)
//                )
//
////            if(connectivityState is InternetState.Available) {
////                viewModel.refresh()
////            }
//            }
//        }


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

