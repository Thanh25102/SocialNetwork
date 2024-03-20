package tech.mobile.social.presentation.app.home.foryou

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.presentation.app.home.post.PostRoute
import tech.mobile.social.presentation.app.home.post.PostState
import java.time.LocalDateTime

@Composable
fun ForYouScreen(
    state: ForYouState,
    actions: ForYouActions,
) {
    LazyColumn {
        items(state.posts.size) {
            PostRoute(state = state.posts[it])
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}

@Composable
@Preview(name = "ForYou")
private fun ForYouScreenPreview() {
    ForYouScreen(
        state = ForYouState(
            arrayListOf(
                PostState(
                    avatarResource = R.drawable.manhthanh_3x4,
                    content = "1nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                    sheetState = false,
                    imageResource = R.drawable.img,
                    authorName = "Thành",
                    postTime = LocalDateTime.now()
                ),
                PostState(
                    avatarResource = R.drawable.manhthanh_3x4,
                    content = "2nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                    sheetState = false,
                    imageResource = R.drawable.img,
                    authorName = "Thành",
                    postTime = LocalDateTime.now()
                ),
                PostState(
                    avatarResource = R.drawable.manhthanh_3x4,
                    content = "3nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                    sheetState = false,
                    imageResource = R.drawable.img,
                    authorName = "Thành",
                    postTime = LocalDateTime.now()
                ),
                PostState(
                    avatarResource = R.drawable.manhthanh_3x4,
                    content = "4nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                    sheetState = false,
                    imageResource = R.drawable.img,
                    authorName = "Thành",
                    postTime = LocalDateTime.now()
                )
            )
        ),
        actions = ForYouActions()
    )
}

