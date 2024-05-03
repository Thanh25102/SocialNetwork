package tech.mobile.social.presentation.app.friend.friendRequest

import android.util.Log
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
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.R
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.repository.AuthorizeRepo
import tech.mobile.social.domain.repository.CommentRepo
import tech.mobile.social.domain.repository.FriendRequestRepo
import tech.mobile.social.domain.usecase.interfaces.FriendRequestUseCase
import tech.mobile.social.shared.UserState
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput

@HiltViewModel
class FriendRequestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val friendRequestUseCase: FriendRequestUseCase,
    private val commentRepo: CommentRepo
) : ViewModel() {

//    val mockFriendRequests = listOf(
//        FriendRequest(1, R.drawable.manhthanh_3x4, "Nguyễn Văn A"),
//        FriendRequest(2, R.drawable.manhthanh_3x4, "Mạnh Thành"),
//        FriendRequest(3, R.drawable.manhthanh_3x4, "Chi Thịnh"),
//        FriendRequest(4, R.drawable.manhthanh_3x4, "Phú Thịnh"),
//        FriendRequest(5, R.drawable.manhthanh_3x4, "Phúc Thịnh"),
//        FriendRequest(6, R.drawable.manhthanh_3x4, "Mạnh Thành"),
//        FriendRequest(7, R.drawable.manhthanh_3x4, "Mạnh Thành"),
//        FriendRequest(8, R.drawable.manhthanh_3x4, "Mạnh Thành"),
//        FriendRequest(9, R.drawable.manhthanh_3x4, "Mạnh Thành"),
//        FriendRequest(10, R.drawable.manhthanh_3x4, "Mạnh Thành"),
//    )

    private val _stateFlow: MutableStateFlow<FriendRequestState> = MutableStateFlow(FriendRequestState())

    val stateFlow: StateFlow<FriendRequestState> = _stateFlow.asStateFlow()

    init {
       val request = getFriendRequests(Optional.present(10), Optional.Present(null), Optional.Absent); // Gọi ở đây
    }

    fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>) { viewModelScope.launch {
//            commentRepo.handleCommentAdded("662f6e4bb4ec3d607efc7d21")
            _stateFlow.value.isLoading = true
            when (val result = friendRequestUseCase.getFriendRequests(take, after, filter)) {
                is ApolloResponse<FriendRequestQuery.Data> -> {
                    _stateFlow.value =
                        FriendRequestState( friendRequests = result.data?.requests)
                }
                null -> {

                }
            }
        }
    }

    fun acceptFriendRequest(requestId: String) {
        _stateFlow.value = _stateFlow.value.copy(
            friendRequests = _stateFlow.value.friendRequests?.edges?.let {
                _stateFlow.value.friendRequests!!.copy( edges = it.map {
                    if (it.node.id == requestId)
                        it.copy( node = it.node.copy(status = RequestStatus.ACCEPTED))
                    else it;
                })
            }
        )

        viewModelScope.launch {
            when (val access = friendRequestUseCase.handleFriendRequest(requestId, RequestStatus.ACCEPTED)) {
                is ApolloResponse<HandleRequestMutation.Data> -> {
                }
                null -> {
                    Log.i("STATE", "err msg accept ")
                    _stateFlow.value.isLoading = false
                }
            }
        }
    }

    fun deleteFriendRequest(request: FriendRequestQuery.Edge) {
        val currentList = _stateFlow.value.friendRequests?.edges?.toMutableList()
        currentList?.remove(request)
        _stateFlow.value = _stateFlow.value.copy(friendRequests = currentList?.let {
            _stateFlow.value.friendRequests?.copy(
                edges = it
            )
        })
    }

    fun refreshFriendRequests() {
//        _stateFlow.value = _stateFlow.value.copy(
//            friendRequests = _stateFlow.value.friendRequests.filterNot { it.isAccepted }
//        )
    }
}