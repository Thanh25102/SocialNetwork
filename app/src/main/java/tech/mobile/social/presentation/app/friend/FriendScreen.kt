package tech.mobile.social.presentation.app.friend

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tech.mobile.social.R
import tech.mobile.social.presentation.app.friend.components.FriendItemComponent
import java.time.LocalDateTime

@Composable
fun FriendScreen(
    state: FriendState,
    actions: FriendActions,
) {
    LazyColumn(){
        items(10){
            FriendItemComponent(
                avatarResource = R.drawable.manhthanh_3x4,
                name = "Manh Thanh",
                time = LocalDateTime.now()
            )
        }
    }
}

@Composable
@Preview(name = "Friend")
private fun FriendScreenPreview() {
    FriendScreen(
        state = FriendState(),
        actions = FriendActions()
    )
}

