package tech.mobile.social.presentation.app.friend.friendSuggest

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
import tech.mobile.social.FriendSuggestQuery
import tech.mobile.social.HandleRequestMutation
import tech.mobile.social.RequestFriendMutation
import tech.mobile.social.domain.repository.CommentRepo
import tech.mobile.social.domain.usecase.interfaces.FriendRequestUseCase
import tech.mobile.social.domain.usecase.interfaces.FriendSuggestUseCase
import tech.mobile.social.type.RequestStatus
import tech.mobile.social.type.RequestWhereInput
import tech.mobile.social.utils.DefaultPaginator

@HiltViewModel
class FriendSuggestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val friendSuggestUseCase: FriendSuggestUseCase,
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

    private val _stateFlow: MutableStateFlow<FriendSuggestState> = MutableStateFlow(FriendSuggestState())

    val stateFlow: StateFlow<FriendSuggestState> = _stateFlow.asStateFlow()

    init {
//       val request = getFriendSuggests(Optional.present(21), Optional.Present(null)); // Gọi ở đây
        Log.d("Gọi query", "haha");
    }

    val paginator = DefaultPaginator(
        initialKey = stateFlow.value.after,
        onLoadUpdated = {
            _stateFlow.value = _stateFlow.value.copy(isLoading = it)
        },
        onRequest = {nextKey -> this.getFriendSuggests(Optional.present(10), nextKey)},
        getNextKey = {
           Optional.present(_stateFlow.value.friendSuggests?.get(_stateFlow.value.friendSuggests?.size!! - 1)?.id)
        },
        onError = {
            _stateFlow.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey  ->
            _stateFlow.value.copy(
                friendSuggests = _stateFlow.value.friendSuggests?.plus(items),
                after = newKey,
                endReached = items.isEmpty()
            )
        },
    )

    suspend fun getFriendSuggests(take: Optional<Int?>, after: Optional<String?>): Result<List<FriendSuggestQuery.Node>> {
        var resultList: List<FriendSuggestQuery.Node> = emptyList();
        viewModelScope.launch {
//            _stateFlow.value.isLoading = true
            when (val result = friendSuggestUseCase.getFriendSuggests(take, after)) {
                is ApolloResponse<FriendSuggestQuery.Data> -> {
                    resultList = result.data?.user?.suggests?.edges?.map { it.node }!!
                    _stateFlow.value =
                        FriendSuggestState( friendSuggests = resultList)
                }
                null -> {

                }
            }
        }

        return Result.success(resultList)
    }

    fun sendFriendRequest(userId: String) {
//        _stateFlow.value = _stateFlow.value.copy(
//            friendSuggests = _stateFlow.value.friendSuggests?.edges?.let {
//                _stateFlow.value.friendSuggests!!.copy( edges = it.map {
//                    if (it.node.id == requestId)
//                        it.copy( node = it.node.copy(status = RequestStatus.ACCEPTED))
//                    else it;
//                })
//            }
//        )
//
        viewModelScope.launch {
            when (val access = friendSuggestUseCase.handleSendFriendRequest(userId)) {
                is ApolloResponse<RequestFriendMutation.Data> -> {
                }
                null -> {
                    Log.i("STATE", "err msg accept ")
                    _stateFlow.value.isLoading = false
                }
            }
        }
    }

    fun deleteFriendRequest(userId: String) {
//        val currentList = _stateFlow.value.friendSuggests?.edges?.toMutableList()
//        currentList?.remove(request)
//        _stateFlow.value = _stateFlow.value.copy(friendSuggests = currentList?.let {
//            _stateFlow.value.friendSuggests?.copy(
//                edges = it
//            )
//        })
//
        viewModelScope.launch {
            when (val access = friendRequestUseCase.handleFriendRequest(userId, RequestStatus.REJECTED)) {
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