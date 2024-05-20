package tech.mobile.social.presentation.app.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.mobile.social.R
import tech.mobile.social.presentation.app.post.PostRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilesScreen(
    state: ProfilesState,
    actions: ProfilesActions,
    onLogout: () -> Unit

) {
    val viewModel: ProfilesViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Trang cá nhân", color = MaterialTheme.colorScheme.primary)
                },
                actions = {
                    IconButton(onClick = {
                        actions.onLogout()
                        onLogout()
                    }) { // please check this too
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Logout"
                        )
                    }
                },
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(11.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.manhthanh_3x4),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    // make text bold
                    Text(text = viewModel.username, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(
                        text = "Chúng ta của tương lai",
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }

            HorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Bạn bè")
                Text(text = "100 người bạn",
                    modifier = Modifier.clickable { actions.onClick() }

                )
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
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            HorizontalDivider()


            LazyColumn {
                state.posts?.let {
                    items(count = it.size) {
                        state.posts[it]?.let { it1 -> PostRoute(state = it1) }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "Profiles")
private fun ProfilesScreenPreview() {
}

