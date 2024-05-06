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
import tech.mobile.social.fragment.RequestFragment
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
        viewModelScope.launch {
            friendRequestUseCase.requestAdded()?.collect{ it.data?.request?.let { _it ->
                Log.d("it",
                    _it.requestFragment.id
                )

                val currentList = _stateFlow.value.friendRequests?.toMutableList()
                currentList?.add(FriendRequestQuery.Node(_it.requestFragment.__typename,_it.requestFragment))
                _stateFlow.value = _stateFlow.value.copy(friendRequests = currentList)
            } }
        }
    }

    fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>) { viewModelScope.launch {
//            commentRepo.handleCommentAdded("662f6e4bb4ec3d607efc7d21")
            _stateFlow.value.isLoading = true
            when (val result = friendRequestUseCase.getFriendRequests(take, after, filter)) {
                is ApolloResponse<FriendRequestQuery.Data> -> {
                    _stateFlow.value =
                        FriendRequestState( friendRequests = result.data?.requests?.edges?.map { it.node })
                }
                null -> {

                }
            }
        }
    }

    fun acceptFriendRequest(request: FriendRequestQuery.Node) {


        val currentList = _stateFlow.value.friendRequests?.toMutableList();
        currentList?.remove(request)
        _stateFlow.value = _stateFlow.value.copy(friendRequests = currentList)

        viewModelScope.launch {
            when (val access = friendRequestUseCase.handleFriendRequest(request.requestFragment.id, RequestStatus.ACCEPTED)) {
                is ApolloResponse<HandleRequestMutation.Data> -> {
                }
                null -> {
                    Log.i("STATE", "err msg accept ")
                    _stateFlow.value.isLoading = false
                }
            }
        }
    }

    fun deleteFriendRequest(request: FriendRequestQuery.Node) {
        val currentList = _stateFlow.value.friendRequests?.toMutableList();
        currentList?.remove(request)
        _stateFlow.value = _stateFlow.value.copy(friendRequests = currentList)

        viewModelScope.launch {
            when (val access = friendRequestUseCase.handleFriendRequest(request.requestFragment.id, RequestStatus.REJECTED)) {
                is ApolloResponse<HandleRequestMutation.Data> -> {
                }
                null -> {
                    Log.i("STATE", "err msg accept ")
                    _stateFlow.value.isLoading = false
                }
            }
        }
    }

    fun refreshFriendRequests() {
//        _stateFlow.value = _stateFlow.value.copy(
//            friendRequests = _stateFlow.value.friendRequests.filterNot { it.isAccepted }
//        )
    }
}