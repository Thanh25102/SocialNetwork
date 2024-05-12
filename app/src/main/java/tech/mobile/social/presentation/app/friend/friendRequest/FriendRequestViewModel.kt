package tech.mobile.social.presentation.app.friend.friendRequest

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
import tech.mobile.social.utils.DefaultPaginator

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

    private val paginator = DefaultPaginator(
        initialKey = stateFlow.value.after,
        onLoadUpdated = {
            _stateFlow.value = _stateFlow.value.copy(isLoading = it)
        },
        onRequest = {nextKey -> this.getFriendRequests(Optional.present(18), nextKey, Optional.Absent) },
        getNextKey = {
            if(it.isNotEmpty()) {
                Optional.present(it[it.size - 1].requestFragment.id)
            } else {
                Optional.Absent
            }
        },
        onError = {
            _stateFlow.value = _stateFlow.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey  ->
            _stateFlow.value = _stateFlow.value.copy(
                friendRequests = _stateFlow.value.friendRequests?.plus(items),
                after = newKey,
                endReached = items.isEmpty(),
            )

            Log.d("newKey",
                newKey.toString()
            )
        },
    )

    init {
        loadNextItems();
//       val request = getFriendRequests(Optional.present(10), Optional.Present(null), Optional.Absent); // Gọi ở đây
        viewModelScope.launch {
            friendRequestUseCase.requestAdded()?.collect{ it.data?.request?.let { _it ->
                Log.d("it",
                    _it.requestFragment.id
                )

                val currentList = _stateFlow.value.friendRequests?.toMutableList()
                currentList?.add(0,FriendRequestQuery.Node(_it.requestFragment.__typename,_it.requestFragment))
                _stateFlow.value = _stateFlow.value.copy(friendRequests = currentList)
            } }
        }
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
        Log.d("Gọi query", "haha");
    }

//    fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>) { viewModelScope.launch {
//            _stateFlow.value.isLoading = true
//            when (val result = friendRequestUseCase.getFriendRequests(take, after, filter)) {
//                is ApolloResponse<FriendRequestQuery.Data> -> {
//                    _stateFlow.value =
//                        FriendRequestState( friendRequests = result.data?.requests?.edges?.map { it.node })
//                }
//                null -> {
//
//                }
//            }
//        }
//    }

    private suspend fun getFriendRequests(take: Optional<Int?>, after: Optional<String?>, filter: Optional<RequestWhereInput?>): kotlin.Result<List<FriendRequestQuery.Node>> =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                val response = friendRequestUseCase.getFriendRequests(take, after, filter)
                kotlin.Result.success(response?.data?.requests?.edges?.map { it.node } ?: emptyList())
            } catch (e: Exception) {
                kotlin.Result.failure(e)
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