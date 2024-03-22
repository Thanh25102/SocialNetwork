package tech.mobile.social.presentation.app.home.foryou

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import tech.mobile.social.R
import tech.mobile.social.presentation.app.home.post.PostState
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
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
            getPosts()
        }
    }

    private fun getPosts(
        skip: Int = 1
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            onRequestSuccess(
                arrayListOf(
                    PostState(
                        avatarResource = R.drawable.manhthanh_3x4,
                        content = "1nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                        sheetState = false,
                        imageResource = R.drawable.img,
                        authorName = "Thành",
                        postTime = LocalDateTime.now()
                    ),
                    PostState(
                        avatarResource = R.drawable.manhthanh_3x4,
                        content = "2nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                        sheetState = false,
                        imageResource = R.drawable.img,
                        authorName = "Thành",
                        postTime = LocalDateTime.now()
                    ),
                    PostState(
                        avatarResource = R.drawable.manhthanh_3x4,
                        content = "3nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                        sheetState = false,
                        imageResource = R.drawable.img,
                        authorName = "Thành",
                        postTime = LocalDateTime.now()
                    ),
                    PostState(
                        avatarResource = R.drawable.manhthanh_3x4,
                        content = "4nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                        sheetState = false,
                        imageResource = R.drawable.img,
                        authorName = "Thành",
                        postTime = LocalDateTime.now()
                    )
                )
            )
        }
    }
    
    fun getPostsPaginated() {
        if (_stateFlow.value.posts.isEmpty()) {
            return
        }

        if (_paginationState.value.endReached) {
            return
        }

        getPosts(_paginationState.value.skip)
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
            getPosts()
            _isRefresh.update { false }
        }

    }

    companion object {
        const val POSTS_LIMIT = 400
    }
}