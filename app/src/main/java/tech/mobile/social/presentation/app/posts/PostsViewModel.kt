package tech.mobile.social.presentation.app.posts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import tech.mobile.social.domain.usecase.interfaces.PostUseCase
import tech.mobile.social.domain.model.post.User
@HiltViewModel
class PostsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postUseCase : PostUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<PostsState> = MutableStateFlow(PostsState())

    val stateFlow: StateFlow<PostsState> = _stateFlow.asStateFlow()


    fun createPost(id: String, content: String, createdAt: java.time.LocalDateTime, createdBy: User) {
        viewModelScope.launch {
            val result = postUseCase.Createpost(id, content, createdAt, createdBy)
        }
    }
}