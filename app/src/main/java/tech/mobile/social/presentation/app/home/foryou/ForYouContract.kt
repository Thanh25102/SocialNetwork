package tech.mobile.social.presentation.app.home.foryou

import androidx.compose.material3.pulltorefresh.PullToRefreshState
import com.apollographql.apollo3.api.Optional
import tech.mobile.social.presentation.app.home.post.PostState
import tech.mobile.social.shared.UserState


/**
 * UI State that represents ForYouScreen
 **/

data class ForYouUiState(
    val forYouState: ForYouState,
    val userState: UserState
)

data class ForYouState(
    val posts: List<PostState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val paginationKey: Int = 0,
    val endReached: Boolean = false,
    val after: Optional<String?> = Optional.Absent
)

data class PagingState(
    val isLoading: Boolean = false,
    val paginationKey: Int = 0,
    val endReached: Boolean = false,
    val after: Optional<String?> = Optional.Absent
)

/**
 * ForYou Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ForYouActions(
    val onClick: () -> Unit = {},
    val onScroll: () -> Unit = {},
)