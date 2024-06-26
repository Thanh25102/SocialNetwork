package tech.mobile.social.presentation.app.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.R
import tech.mobile.social.presentation.app.home.components.ScrollButton
import tech.mobile.social.presentation.app.post.PostRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    actions: HomeActions,
) {
    val (homeState) = state

    val lazyListState = rememberLazyListState()
    val lottieComp by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    val lottieProgress by animateLottieCompositionAsState(
        composition = lottieComp,
        iterations = LottieConstants.IterateForever,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Trang chủ", color = MaterialTheme.colorScheme.primary)
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(state = lazyListState) {
                items(homeState.posts.size) {
                    if (state.homeState.after != Optional.Absent && it >= state.homeState.posts.size - 1 && !state.homeState.endReached && !state.homeState.isLoading) {
                        actions.onScroll();
                    }
                    PostRoute(state = homeState.posts[it])
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    if (homeState.isLoading) {
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
            when {
                homeState.isLoading -> {
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

                homeState.error.isNotEmpty() -> {

                    Text(
                        text = homeState.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )

                }
            }
            ScrollButton(lazyListState = lazyListState)
        }
    }


}

@Composable
@Preview(name = "Home")
private fun HomeScreenPreview() {

}

