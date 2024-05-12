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
import tech.mobile.social.ReactionAddedSubscription
import tech.mobile.social.RequestAddedSubscription
import tech.mobile.social.RequestHandledSubscription
import tech.mobile.social.domain.usecase.interfaces.CommentUseCase
import tech.mobile.social.domain.usecase.interfaces.FriendRequestUseCase
import tech.mobile.social.domain.usecase.interfaces.NotificationUseCase
import tech.mobile.social.domain.usecase.interfaces.ReactionUseCase
import tech.mobile.social.fragment.CommentFragment
import tech.mobile.social.fragment.CommentNotification
import tech.mobile.social.fragment.ReactionNotification
import tech.mobile.social.fragment.RequestFragment
import tech.mobile.social.fragment.RequestNotification
import tech.mobile.social.fragment.SenderNotification
import tech.mobile.social.presentation.app.friend.friendRequest.FriendRequestState
import tech.mobile.social.type.NotificationType
import tech.mobile.social.type.Reaction
import tech.mobile.social.type.RequestWhereInput

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val notificationUseCase: NotificationUseCase,
    private val commentUseCase: CommentUseCase,
    private val reactionUseCase: ReactionUseCase,
    private val friendRequestUseCase: FriendRequestUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<NotificationsState> = MutableStateFlow(NotificationsState())
    val stateFlow: StateFlow<NotificationsState> = _stateFlow.asStateFlow()

    init {
        getNotifications(Optional.present(10), Optional.Present(null));

        viewModelScope.launch {
            reactionUseCase.handlerReactionAdded()?.collect{ it.data?.reaction?.let { _it ->
//                Log.d("it",
//                    _it.content
//                )


                val currentList = _stateFlow.value.notifications?.toMutableList()

                when(_it) {
                    is ReactionAddedSubscription.Reaction -> {
//                        Log.d("it",
//                            _it.commentFragment.content
//                        )
                        currentList?.add(0,
                            Notification(
                                id = _it.reactionFragment.id,
                                type = NotificationType.LIKE,
                                senderNotification = SenderNotification(sender = SenderNotification.Sender(_it.reactionFragment.user.id, _it.reactionFragment.user.username)),
                                createdAt = _it.reactionFragment.createdAt,
                                reactionNotification = ReactionNotification(ReactionNotification.Reaction(_it.reactionFragment.id))

                            ))
                        _stateFlow.value = _stateFlow.value.copy(notifications = currentList)
                        //NotificationsQuery.Node("",type = NotificationType.COMMENT, senderNotification = Optional.Absent, commentNotification = CommentNotification(CommentNotification.Comment(CommentFragment(_it.commentFragment.id,_it.commentFragment.content, _it.commentFragment.user)))
                    }
                }


            } }
        }

        viewModelScope.launch {
            commentUseCase.handleCommentAdded()?.collect{ it.data?.comment?.let { _it ->
//                Log.d("it",
//                    _it.content
//                )


                val currentList = _stateFlow.value.notifications?.toMutableList()

                when(_it) {
                    is CommentAddedSubscription.Comment -> {
                        Log.d("it",
                            _it.commentFragment.content
                        )
                        currentList?.add(0,
                                Notification(
                                    id = _it.commentFragment.id,
                                    type = NotificationType.COMMENT,
                                    senderNotification = SenderNotification(sender = SenderNotification.Sender(_it.commentFragment.user.id, _it.commentFragment.user.username)),
                                    createdAt = _it.commentFragment.createdAt,
                                    commentNotification = CommentNotification(CommentNotification.Comment("",CommentFragment(_it.commentFragment.id, _it.commentFragment.content, _it.commentFragment.user,_it.commentFragment.createdAt)))

                                ))
                        _stateFlow.value = _stateFlow.value.copy(notifications = currentList)
                        //NotificationsQuery.Node("",type = NotificationType.COMMENT, senderNotification = Optional.Absent, commentNotification = CommentNotification(CommentNotification.Comment(CommentFragment(_it.commentFragment.id,_it.commentFragment.content, _it.commentFragment.user)))
                    }
                }


            } }
        }

        viewModelScope.launch {
            friendRequestUseCase.requestAdded()?.collect {
                it.data?.request?.let { _it ->
//                Log.d("it",
//                    _it.content
//                )


                    val currentList = _stateFlow.value.notifications?.toMutableList()

                    when (_it) {
                        is RequestAddedSubscription.Request -> {
                            Log.d(
                                "it",
                                _it.requestFragment.id
                            )
                            currentList?.add(
                                0,
                                Notification(
                                    id = _it.requestFragment.id,
                                    type = NotificationType.FRIEND_REQUEST,
                                    senderNotification = SenderNotification(
                                        sender = SenderNotification.Sender(
                                            _it.requestFragment.senderRequest.sender.id,
                                            _it.requestFragment.senderRequest.sender.username
                                        )
                                    ),
                                    createdAt = _it.requestFragment.createdAt,
                                    requestNotification = RequestNotification(
                                        RequestNotification.Request(
                                            _it.requestFragment.id
                                        )
                                    )

                                )
                            )
                            _stateFlow.value = _stateFlow.value.copy(notifications = currentList)
                            //NotificationsQuery.Node("",type = NotificationType.COMMENT, senderNotification = Optional.Absent, commentNotification = CommentNotification(CommentNotification.Comment(CommentFragment(_it.commentFragment.id,_it.commentFragment.content, _it.commentFragment.user)))
                        }
                    }


                }
            }
        }

        viewModelScope.launch {
            friendRequestUseCase.requestHandled()?.collect{ it.data?.request?.let { _it ->
//                Log.d("it",
//                    _it.content
//                )


                val currentList = _stateFlow.value.notifications?.toMutableList()

                when(_it) {
                    is RequestHandledSubscription.Request -> {
                        Log.d("it",
                            _it.requestFragment.id
                        )
                        currentList?.add(0,
                            Notification(
                                id = _it.requestFragment.id,
                                type = NotificationType.FRIEND_REQUEST_ACCEPTED,
                                senderNotification = SenderNotification(sender = SenderNotification.Sender(_it.requestFragment.senderRequest.sender.id, _it.requestFragment.senderRequest.sender.username)),
                                createdAt = _it.requestFragment.createdAt,
                                requestNotification = RequestNotification(RequestNotification.Request(_it.requestFragment.id))

                            ))
                        _stateFlow.value = _stateFlow.value.copy(notifications = currentList)
                        //NotificationsQuery.Node("",type = NotificationType.COMMENT, senderNotification = Optional.Absent, commentNotification = CommentNotification(CommentNotification.Comment(CommentFragment(_it.commentFragment.id,_it.commentFragment.content, _it.commentFragment.user)))
                    }
                }


            } }
        }
    }

    fun getNotifications(take: Optional<Int?>, after: Optional<String?>) {
        viewModelScope.launch {
        _stateFlow.value.isLoading = true
        when (val result = notificationUseCase.getNotifications(take, after)) {
            is ApolloResponse<NotificationsQuery.Data> -> {
                _stateFlow.value =
                    NotificationsState( notifications = result.data?.notifications?.edges?.map { Notification(it.node.id,it.node.type,it.node.createdAt, senderNotification = it.node.senderNotification, commentNotification = it.node.commentNotification, requestNotification = it.node.requestNotification, recipientsNotification = it.node.recipientNotification, postNotification = it.node.postNotification) })

            }
            null -> {

            }
        }
    }
    }

}