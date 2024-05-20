package tech.mobile.social.presentation.app.post

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.mobile.social.CommentsQuery
import tech.mobile.social.CreatePostMutation
import tech.mobile.social.ReactionPostMutation
import tech.mobile.social.domain.usecase.interfaces.CommentUseCase
import tech.mobile.social.domain.usecase.interfaces.PostUseCase
import tech.mobile.social.domain.usecase.interfaces.ReactionUseCase
import tech.mobile.social.type.CommentWhereInput
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val commentUseCase: CommentUseCase,
    private val postUseCase: PostUseCase,
    private val reactionUseCase: ReactionUseCase
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(
        State(
            likes = 52,
            sheetState = false,
            isLiked = false,
            friends = listOf("Phương Nghi", "Phương Uyên"),
            commentsCount = 50,
            comments = emptyList(),
            post = null
        )
    )
    val stateFlow: StateFlow<State> = _stateFlow.asStateFlow()

    init {
        getComments(Optional.present(10), Optional.Present(null), Optional.Absent)
        _stateFlow.value = _stateFlow.value.copy(
            likes = _stateFlow.value.post?.likes ?: 0
        )
    }

    fun getComments(
        take: Optional<Int?>,
        after: Optional<String?>,
        filter: Optional<CommentWhereInput?>
    ) {
        viewModelScope.launch {
            when (val result = commentUseCase.getComments(take, after, filter)) {
                is ApolloResponse<CommentsQuery.Data> -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        comments = result.data?.comments?.edges?.map { it.node }
                    )
                }
                else -> {
                    // handle error
                }
            }
        }
    }

    fun sharePost(postId: String) {
        viewModelScope.launch {
            when (val result = postUseCase.Createpost(Optional.present(postId), Optional.Absent, Optional.Absent)) {
                is ApolloResponse<CreatePostMutation.Data> -> {
                    // handle success
                }
                else -> {
                    // handle error
                }
            }
        }
    }

    fun reactionPost(postId: String) {
        viewModelScope.launch {
            val result = reactionUseCase.reactionPost(postId)
            if (result?.data?.reaction != null) {
                Log.e("REACTION", "SUCCESS")
                _stateFlow.value = _stateFlow.value.copy(
                    likes = _stateFlow.value.likes + 1,
                    post = _stateFlow.value.post?.copy(likes = _stateFlow.value.post?.likes?.plus(1)),
                    isLiked = true
                )
            } else {
                Log.e("REACTION", "FAILED")
                _stateFlow.value = _stateFlow.value.copy(
                    likes = _stateFlow.value.likes - 1,
                    post = _stateFlow.value.post?.copy(likes = _stateFlow.value.post?.likes?.minus(1)),
                    isLiked = false
                )
            }
        }
    }

    fun doCloseComments() {
        _stateFlow.value = _stateFlow.value.copy(sheetState = false)
    }

    fun doOpenComments() {
        _stateFlow.value = _stateFlow.value.copy(sheetState = true)
    }
}
