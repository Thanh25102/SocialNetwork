package tech.mobile.social.presentation.app.posts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents PostsScreen
 **/
class PostsState {

}

/**
 * Posts Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class PostsActions(
    val onClick: () -> Unit = {}
)