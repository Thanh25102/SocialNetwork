package tech.mobile.social.presentation.app.profiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.mobile.social.R
import tech.mobile.social.presentation.app.components.PostComponent
import tech.mobile.social.presentation.utils.formatTimeAgo
import java.time.LocalDateTime

@Composable
fun ProfilesScreen(
    state: ProfilesState,
    actions: ProfilesActions,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.manhthanh_3x4),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(text = "Bùi Mạnh Thành")
                Text(text = "Chúng ta của tương lai")
                Text(text = formatTimeAgo(LocalDateTime.now()))
            }
        }

        HorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Bạn bè")
            Text(text = "100 người bạn")
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(8) {
                Image(
                    painter = painterResource(id = R.drawable.manhthanh_3x4),
                    contentDescription = "Avatar",
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        HorizontalDivider()

        Text(text = "Bài viết")

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
}

@Composable
@Preview(name = "Profiles")
private fun ProfilesScreenPreview() {
    ProfilesScreen(
        state = ProfilesState(),
        actions = ProfilesActions()
    )
}

