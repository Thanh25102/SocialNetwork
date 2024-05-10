package tech.mobile.social.presentation.app.home.post

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
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.R
import tech.mobile.social.domain.usecase.interfaces.CommentUseCase
import tech.mobile.social.presentation.app.friend.friendRequest.FriendRequestState
import tech.mobile.social.type.CommentWhereInput
import tech.mobile.social.type.RequestWhereInput
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val commentUseCase: CommentUseCase
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(
        State(
            52, false, false, listOf("Phương Nghi", "Phương Uyên"), 50,
            listOf(
//                Comment(
//                    avatarResource = R.drawable.manhthanh_3x4,
//                    author = "Đại Lộc",
//                    time = LocalDateTime.now(),
//                    content = "Mày bị ngu à ?"
//                ),
//                Comment(
//                    avatarResource = R.drawable.manhthanh_3x4,
//                    author = "Tuấn Anh",
//                    time = LocalDateTime.now(),
//                    content = "Are u ỗn ?"
//                ),
//                Comment(
//                    avatarResource = R.drawable.manhthanh_3x4,
//                    author = "Minh Thông",
//                    time = LocalDateTime.now(),
//                    content = "Chị đẹp lắm"
//                ),
//                Comment(
//                    avatarResource = R.drawable.manhthanh_3x4,
//                    author = "Khánh Hiền",
//                    time = LocalDateTime.now(),
//                    content = "Ahihi"
//                )
            ),
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

    fun getComments(take: Optional<Int?>, after: Optional<String?>, filter: Optional<CommentWhereInput?>) { viewModelScope.launch {
        when (val result = commentUseCase.getComments(take, after, filter)) {
            is ApolloResponse<CommentsQuery.Data> -> {
                _stateFlow.value.comments = result.data?.comments?.edges?.map { it.node }
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