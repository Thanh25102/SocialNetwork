package tech.mobile.social.presentation.app.post

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
import tech.mobile.social.PostQuery
import tech.mobile.social.domain.usecase.interfaces.CommentUseCase
import tech.mobile.social.domain.usecase.interfaces.PostUseCase
import tech.mobile.social.type.CommentWhereInput
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val commentUseCase: CommentUseCase,
    private val postUseCase: PostUseCase
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(
        State(
            52, false, false, listOf("Phương Nghi", "Phương Uyên"), 50,
            listOf(),
            null
        )
    )
    val stateFlow: StateFlow<State> = _stateFlow.asStateFlow()

    init {
        val comments = getComments(Optional.present(10), Optional.Present(null), Optional.Absent);
        viewModelScope.launch {
//            friendRequestUseCase.requestAdded()?.collect{ it.data?.request?.let { _it ->
//                Log.d("it",
//                    _it.requestFragment.id
//                )
//
//                val currentList = _stateFlow.value.friendRequests?.toMutableList()
//                currentList?.add(FriendRequestQuery.Node(_it.requestFragment.__typename,_it.requestFragment))
//                _stateFlow.value = _stateFlow.value.copy(friendRequests = currentList)
//            } }
        }
    }

    fun getComments(
        take: Optional<Int?>,
        after: Optional<String?>,
        filter: Optional<CommentWhereInput?>
    ) {
        viewModelScope.launch {
            when (val result = commentUseCase.getComments(take, after, filter)) {
                is ApolloResponse<CommentsQuery.Data> -> {
                    _stateFlow.value.comments = result.data?.comments?.edges?.map { it.node }
                }

                null -> {

                }
            }
        }
    }

    fun sharePost(postId: String) {
        viewModelScope.launch {
            when (val result = postUseCase.Createpost(Optional.present(postId), Optional.Absent, Optional.Absent)) {
                is ApolloResponse<CreatePostMutation.Data> -> {

                }

                null -> {

                }
            }
        }
    }

    fun doCloseComments() {
        val currentState = _stateFlow.value
        _stateFlow.value = currentState.copy(sheetState = false)
    }

    fun doOpenComments() {
        val currentState = _stateFlow.value
        _stateFlow.value = currentState.copy(sheetState = true)
    }
}