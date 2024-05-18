package tech.mobile.social.presentation.app.home

import com.apollographql.apollo3.api.Optional
import tech.mobile.social.presentation.app.post.PostState
import tech.mobile.social.shared.UserState


/**
 * UI State that represents ForYouScreen
 **/

data class HomeUiState(
    val homeState: HomeState,
    val userState: UserState
)

data class HomeState(
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
data class HomeActions(
    val onClick: () -> Unit = {},
    val onScroll: () -> Unit = {},
)