package tech.mobile.social.presentation.app.home.foryou

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
import com.airbnb.lottie.compose.*
import tech.mobile.social.R
import tech.mobile.social.presentation.app.home.foryou.components.InfiniteListHandler
import tech.mobile.social.presentation.app.home.foryou.components.ScrollButton
import tech.mobile.social.presentation.app.home.post.PostRoute
import tech.mobile.social.presentation.app.home.post.PostState
import tech.mobile.social.shared.UserState
import java.util.Date

@Composable
fun ForYouScreen(
    state: ForYouUiState,
    actions: ForYouActions,
) {
    val (forYouState, paginationState, isRefreshState, userState) = state

    val lazyListState = rememberLazyListState()
    val lottieComp by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    val lottieProgress by animateLottieCompositionAsState(
        composition = lottieComp,
        iterations = LottieConstants.IterateForever,
    )

    LazyColumn(state = lazyListState) {
        items(forYouState.posts.size) {
            PostRoute(state = forYouState.posts[it])
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            if (paginationState.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
    InfiniteListHandler(lazyListState = lazyListState) {
        actions.onScroll()
    }
    when {
        forYouState.isLoading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                LottieAnimation(
                    composition = lottieComp,
                    progress = { lottieProgress },
                )
            }
        }

        forYouState.error.isNotEmpty() -> {

            Text(
                text = forYouState.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

//            if(connectivityState is InternetState.Available) {
//                viewModel.refresh()
//            }
        }
    }
    ScrollButton(lazyListState = lazyListState)
}

@Composable
@Preview(name = "ForYou")
private fun ForYouScreenPreview() {
    ForYouScreen(
        state = ForYouUiState(
            ForYouState(
                arrayListOf(
                    PostState(
                        avatarResource = R.drawable.manhthanh_3x4,
                        content = "1nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                        sheetState = false,
                        imageResource = R.drawable.img,
                        authorName = "Thành",
                        postTime = Date()
                    ),
                    PostState(
                        avatarResource = R.drawable.manhthanh_3x4,
                        content = "2nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                        sheetState = false,
                        imageResource = R.drawable.img,
                        authorName = "Thành",
                        postTime = Date()
                    ),
                    PostState(
                        avatarResource = R.drawable.manhthanh_3x4,
                        content = "3nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                        sheetState = false,
                        imageResource = R.drawable.img,
                        authorName = "Thành",
                        postTime = Date()
                    ),
                    PostState(
                        avatarResource = R.drawable.manhthanh_3x4,
                        content = "4nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                        sheetState = false,
                        imageResource = R.drawable.img,
                        authorName = "Thành",
                        postTime = Date()
                    )
                )
            ),
            PagingState(),
            false,
            UserState(null, false)
        ),
        actions = ForYouActions()
    )
}

