package tech.mobile.social.presentation.app.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.mobile.social.R
import tech.mobile.social.UserprofileQuery
import tech.mobile.social.domain.usecase.interfaces.ProfileUseCase
import tech.mobile.social.fragment.Posts
import tech.mobile.social.presentation.app.friend.friendRequest.FriendRequestState
import tech.mobile.social.presentation.app.post.PostState
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ProfilesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val profileUseCase: ProfileUseCase
) : ViewModel() {
    private val _stateFlow: MutableStateFlow<ProfilesState> = MutableStateFlow(ProfilesState())
    val stateFlow: StateFlow<ProfilesState> = _stateFlow.asStateFlow()
    var username: String = ""
    fun onOpenFriend() {

    }


    init {
        if (_stateFlow.value.posts?.isEmpty() == true) {
            val respone = getProfile()
        }

    }

    //  private fun ProfilesState(): ProfilesState {

    //  }

    fun getProfile() {
        viewModelScope.launch {
            when (val result = profileUseCase.Getallpost()) {
                is ApolloResponse<UserprofileQuery.Data> -> {
                    username = result.data?.user?.username.toString()
                    _stateFlow.value =
                        result.data?.user?.posts?.posts?.edges?.map {
                            it.node.content?.let { it1 ->
                                PostState(
                                    avatarResource = R.drawable.manhthanh_3x4,
                                    content = it1,
                                    sheetState = false,
                                    imageResource = R.drawable.img,
                                    authorName = username,
                                    postTime = "",
                                    comments = null,
                                    image = it.node.file?.path
                                )
                            }
                        }?.let { ProfilesState(posts = it) }!!
                }

                null -> {

                }
            }
        }
    }


    /*    private val _stateFlow: MutableStateFlow<ProfilesState> = MutableStateFlow(
                 ProfilesState(
                arrayListOf(
                       PostState(
                           avatarResource = R.drawable.manhthanh_3x4,
                           content = "1nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                           sheetState = false,
                           imageResource = R.drawable.img,
                           authorName = "Thành",
                           postTime = Date()
                       ),
                       PostState(
                           avatarResource = R.drawable.manhthanh_3x4,
                           content = "1nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                           sheetState = false,
                           imageResource = R.drawable.img,
                           authorName = "Thành",
                           postTime = Date()
                       ),
                       PostState(
                           avatarResource = R.drawable.manhthanh_3x4,
                           content = "2nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                           sheetState = false,
                           imageResource = R.drawable.img,
                           authorName = "Thành",
                           postTime = Date()
                       ),
                       PostState(
                           avatarResource = R.drawable.manhthanh_3x4,
                           content = "3nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                           sheetState = false,
                           imageResource = R.drawable.img,
                           authorName = "Thành",
                           postTime = Date()
                       ),
                       PostState(
                           avatarResource = R.drawable.manhthanh_3x4,
                           content = "4nam tay nhau that chat, giu tay nhau that lau, hua voi anh mot cau se di chon toi cuoi con duong den khi tim ngung dap  va doi chan ngung di ....",
                           sheetState = false,
                           imageResource = R.drawable.img,
                           authorName = "Thành",
                           postTime = Date()
                       )
                   )
               )
           )

    val stateFlow: StateFlow<ProfilesState> = _stateFlow.asStateFlow()
*/
}