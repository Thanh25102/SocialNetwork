package tech.mobile.social.presentation.app.home.foryou

import androidx.compose.material3.pulltorefresh.PullToRefreshState
import tech.mobile.social.presentation.app.home.post.PostState
import tech.mobile.social.shared.UserState


/**
 * UI State that represents ForYouScreen
 **/

data class ForYouUiState(
    val forYouState: ForYouState,
    val pagingState: PagingState,
    val refreshState: Boolean,
    val userState: UserState
)

data class ForYouState(
    val posts: List<PostState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)

data class PagingState(
    val isLoading: Boolean = false,
    val paginationKey: Int = 0,
    val skip: Int = 1,
    val endReached: Boolean = false
)

/**
 * ForYou Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ForYouActions(
    val onClick: () -> Unit = {},
    val onScroll: () -> Unit = {},
)