package tech.mobile.social.presentation.app.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.mobile.social.R
import tech.mobile.social.presentation.app.home.post.PostState
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ProfilesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ProfilesState> = MutableStateFlow(
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

}