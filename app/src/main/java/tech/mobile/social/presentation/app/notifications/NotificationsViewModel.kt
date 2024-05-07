package tech.mobile.social.presentation.app.notifications

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
import tech.mobile.social.CommentAddedSubscription
import tech.mobile.social.FriendRequestQuery
import tech.mobile.social.NotificationsQuery
import tech.mobile.social.domain.usecase.interfaces.CommentUseCase
import tech.mobile.social.domain.usecase.interfaces.NotificationUseCase
import tech.mobile.social.fragment.CommentFragment
import tech.mobile.social.fragment.CommentNotification
import tech.mobile.social.presentation.app.friend.friendRequest.FriendRequestState
import tech.mobile.social.type.NotificationType
import tech.mobile.social.type.RequestWhereInput

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val notificationUseCase: NotificationUseCase,
    private val commentUseCase: CommentUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<NotificationsState> = MutableStateFlow(NotificationsState())

    val stateFlow: StateFlow<NotificationsState> = _stateFlow.asStateFlow()

    init {
        getNotifications(Optional.present(10), Optional.Present(null));

        viewModelScope.launch {
            commentUseCase.handleCommentAdded()?.collect{ it.data?.comment?.let { _it ->
//                Log.d("it",
//                    _it.content
//                )


                val currentList = _stateFlow.value.notifications?.toMutableList()

                when(_it) {
                    is CommentAddedSubscription.Comment -> {
//                        currentList?.add(NotificationsQuery.Node("",type = NotificationType.COMMENT, senderNotification = Optional.Absent, commentNotification = CommentNotification(CommentNotification.Comment(CommentFragment(_it.commentFragment.id,_it.commentFragment.content, _it.commentFragment.user))))
                    }
                }

//                _stateFlow.value = _stateFlow.value.copy(friendRequests = currentList)
            } }
        }
    }

    fun getNotifications(take: Optional<Int?>, after: Optional<String?>) {
        viewModelScope.launch {
        _stateFlow.value.isLoading = true
        when (val result = notificationUseCase.getNotifications(take, after)) {
            is ApolloResponse<NotificationsQuery.Data> -> {
                _stateFlow.value =
                    NotificationsState( notifications = result.data?.notifications?.edges?.map { it.node })
            }
            null -> {

            }
        }
    }
    }

}