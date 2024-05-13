package tech.mobile.social.presentation.app.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.Optional
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.mobile.social.R
import tech.mobile.social.domain.usecase.interfaces.PostUseCase
import tech.mobile.social.presentation.app.post.PostState
import tech.mobile.social.utils.DefaultPaginator
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeState> = MutableStateFlow(
        HomeState()
    )
    val stateFlow: StateFlow<HomeState> = _stateFlow.asStateFlow()

    val paginator = DefaultPaginator(
        initialKey = _stateFlow.value.after,
        onLoadUpdated = {
            _stateFlow.value = _stateFlow.value.copy(isLoading = it)
        },
        onRequest = { nextKey -> this._getPosts(Optional.present(18), nextKey) },
        getNextKey = {
            if (it.isNotEmpty()) {
                Optional.present(it[it.size - 1].id)
            } else {
                Optional.Absent
            }
        },
        onError = {
            _stateFlow.value = _stateFlow.value.copy(error = it?.message ?: "")
        },
        onSuccess = { items, newKey ->
            _stateFlow.value = _stateFlow.value.copy(
                posts = _stateFlow.value.posts.plus(items) ?: emptyList(),
                after = newKey,
                endReached = items.isEmpty(),
            )
        },
    )

    init {
        loadNextItems()
    }


    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    suspend fun _getPosts(take: Optional<Int?>, after: Optional<String?>): Result<List<PostState>> =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                val response = postUseCase.NewsFeed(take, after)
                Result.success(
                    response?.data?.user?.friends?.edges?.map {
                        it.node.friend.posts.posts.edges.map {
                            it.node.content?.let { it1 ->
                                PostState(
                                    id = it.node.id,
                                    likes = it.node.reactions.edges.size,
                                    commentsCount = it.node.comments.edges.size,
                                    avatarResource = R.drawable.manhthanh_3x4,
                                    content = it1,
                                    sheetState = false,
                                    imageResource = R.drawable.img,
                                    authorName = it.node.user.username,
                                    postTime = it.node.createdAt,
                                    image = it.node.file?.path,
                                    comments = it.node.comments
                                )
                            }
                        }
                    }?.flatten() as List<PostState>? ?: emptyList()
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

}