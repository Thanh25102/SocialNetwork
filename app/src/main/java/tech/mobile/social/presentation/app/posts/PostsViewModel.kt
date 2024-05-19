package tech.mobile.social.presentation.app.posts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.mobile.social.CreatePostMutation
import tech.mobile.social.domain.usecase.interfaces.PostUseCase
import tech.mobile.social.domain.model.post.User

@HiltViewModel
class PostsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<PostsState> = MutableStateFlow(PostsState())

    val stateFlow: StateFlow<PostsState> = _stateFlow.asStateFlow()

    fun doContentChange(content: String) {
        _stateFlow.value = _stateFlow.value.copy(content = content)
    }

    fun createPost() {
        viewModelScope.launch {
            when (val result = postUseCase.Createpost(
                Optional.Absent,
                Optional.present(_stateFlow.value.content),
                Optional.Absent
            )) {
                is ApolloResponse<CreatePostMutation.Data> -> {
                    _stateFlow.value = _stateFlow.value.copy(content = "")
                }

                null -> {

                }
            }
        }
    }
}