package tech.mobile.social.presentation.app.home2.foryou

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tech.mobile.social.R
import tech.mobile.social.presentation.app.components.PostComponent
import java.time.LocalDateTime

@Composable
fun ForYouScreen(
    state: ForYouState,
    actions: ForYouActions,
) {

    LazyColumn {
        items(10) {
            PostComponent(
                R.drawable.manhthanh_3x4,
                "Thành",
                LocalDateTime.now(),
                "nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                R.drawable.img
            )
        }
    }
}

@Composable
@Preview(name = "ForYou")
private fun ForYouScreenPreview() {
    ForYouScreen(
        state = ForYouState(),
        actions = ForYouActions()
    )
}

