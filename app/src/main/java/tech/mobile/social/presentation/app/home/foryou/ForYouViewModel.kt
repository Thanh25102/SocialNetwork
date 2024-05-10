package tech.mobile.social.presentation.app.home.foryou

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import tech.mobile.social.NewsfeedQuery
import tech.mobile.social.R
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.usecase.interfaces.PostUseCase
import tech.mobile.social.presentation.app.home.post.PostState
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ForYouState> = MutableStateFlow(
        ForYouState()
    )
    val stateFlow: StateFlow<ForYouState> = _stateFlow.asStateFlow()

    private val _paginationState = MutableStateFlow(PagingState())
    val paginationState: StateFlow<PagingState> = _paginationState.asStateFlow()


    private val _isRefresh = MutableStateFlow(false)
    val isRefresh: StateFlow<Boolean> = _isRefresh

    init {
        if (_stateFlow.value.posts.isEmpty()) {
//            getPosts()
//            getPosts(Optional.Present(10), Optional.Present(null));
            _getPosts(Optional.Present(10), Optional.Present(null));
        }
    }

//    private fun getPosts(
//        skip: Int = 1
//    ) {
//        viewModelScope.launch(Dispatchers.IO) {
//            onRequestLoading()
//
//            when (val result = postUseCase.Getpost()) {
//                is Result.Success -> {
//                    onRequestSuccess(
//                        result.data.posts.map {
//                            PostState(
//                                avatarResource = R.drawable.manhthanh_3x4,
//                                content = it.content,
//                                sheetState = false,
//                                imageResource = R.drawable.img,
//                                authorName = it.createdBy.username,
//                                postTime = it.createdAt,
//                                image = it.image
//                            )
//                        }
//                    )
//                }
//
//                is Result.Error -> {
//                    _stateFlow.update {
//                        it.copy(
//                            isLoading = false,
//                            error = result.error.message.get(0)
//                        )
//                    }
//                }
//            }
//
//        }
//    }

    private fun _getPosts(take: Optional<Int?>, after: Optional<String?>){
        viewModelScope.launch(Dispatchers.IO) {
            onRequestLoading()

            when (val result = postUseCase.NewsFeed(take, after)) {
                is ApolloResponse<NewsfeedQuery.Data>? -> {
                    result?.data?.user?.friends?.edges?.map {
                        onRequestSuccess(it.node.friend.posts.posts.edges.map {
                            PostState(
                                avatarResource = R.drawable.manhthanh_3x4,
                                content = it.node.content,
                                sheetState = false,
                                imageResource = R.drawable.img,
                                authorName = it.node.user.username,
                                postTime = it.node.createdAt,
                                image = it.node.file?.path
                            )
                        })
                    }
                }
                null -> {

                }

            }
        }
    }



    private fun onRequestLoading() {
        if (_stateFlow.value.posts.isEmpty()) {
            _stateFlow.update {
                it.copy(
                    isLoading = true
                )
            }
        }

        if (_stateFlow.value.posts.isNotEmpty()) {
            _paginationState.update {
                it.copy(
                    isLoading = true
                )
            }
        }
    }

    fun getPostsPaginated() {
        if (_stateFlow.value.posts.isEmpty()) {
            return
        }

        if (_paginationState.value.endReached) {
            return
        }

//        _getPosts(_paginationState.value.skip)
    }

    private fun onRequestSuccess(
        data: List<PostState>
    ) {
        val posts = _stateFlow.value.posts + data
        _stateFlow.update {
            it.copy(
                posts = posts.toImmutableList(),
                isLoading = false,
                error = ""
            )
        }

        val listSize = _stateFlow.value.posts.size
        _paginationState.update {
            it.copy(
                skip = it.skip + 1,
                endReached = data.isEmpty() || listSize >= POSTS_LIMIT,
                isLoading = false
            )
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefresh.update { true }
            _paginationState.update { it.copy(skip = 0) }
            _stateFlow.update { it.copy(posts = emptyList()) }
            _getPosts()
            _isRefresh.update { false }
        }

    }

    companion object {
        const val POSTS_LIMIT = 400
    }
}