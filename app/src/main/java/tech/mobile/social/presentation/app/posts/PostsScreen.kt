package tech.mobile.social.presentation.app.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.rounded.GroupAdd
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import tech.mobile.social.R

import tech.mobile.social.ui.theme.BtnColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    state: PostsState,
    actions: PostsActions,

    ) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tạo bài viết", color = MaterialTheme.colorScheme.primary)
                },

                actions = {
                    IconButton(onClick = { actions.onClick() }) { // please check this too
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "Create post"
                        )
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.manhthanh_3x4),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Bùi Mạnh Thành", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }

            }

            TextField(
                value = state.content,
                onValueChange = {
                    actions.onContentChange(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                placeholder = { Text("Bạn đang nghĩ gì?") },
            )

        }
    }


}

@Composable
@Preview(name = "Posts")
private fun PostsScreenPreview() {
    val navController = rememberNavController()
    PostsScreen(
        state = PostsState(),
        actions = PostsActions(),
        /*navController = navController*/
    )
}

