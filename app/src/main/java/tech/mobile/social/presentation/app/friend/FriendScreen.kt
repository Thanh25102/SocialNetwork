package tech.mobile.social.presentation.app.friend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.R
import tech.mobile.social.presentation.app.friend.components.FriendItemComponent
import java.time.LocalDateTime

@Composable
fun FriendScreen(
    state: FriendState,
    actions: FriendActions,
) {
    Column {
        Text(
            text = "Danh sách bạn bè", fontWeight = FontWeight.Bold, fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            items(10) {
                FriendItemComponent(
                    avatarResource = R.drawable.manhthanh_3x4,
                    name = "Manh Thanh",
                    time = LocalDateTime.now()
                )
            }
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

