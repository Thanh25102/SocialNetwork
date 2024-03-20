package tech.mobile.social.presentation.app.home.post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.mobile.social.R
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(
        State(
            52, false, false, listOf("Phương Nghi", "Phương Uyên"), 50,
            listOf(
                Comment(
                    avatarResource = R.drawable.manhthanh_3x4,
                    author = "Đại Lộc",
                    time = LocalDateTime.now(),
                    content = "Mày bị ngu à ?"
                ),
                Comment(
                    avatarResource = R.drawable.manhthanh_3x4,
                    author = "Tuấn Anh",
                    time = LocalDateTime.now(),
                    content = "Are u ỗn ?"
                ),
                Comment(
                    avatarResource = R.drawable.manhthanh_3x4,
                    author = "Minh Thông",
                    time = LocalDateTime.now(),
                    content = "Chị đẹp lắm"
                ),
                Comment(
                    avatarResource = R.drawable.manhthanh_3x4,
                    author = "Khánh Hiền",
                    time = LocalDateTime.now(),
                    content = "Ahihi"
                )
            ),
            null
        )
    )
    val stateFlow: StateFlow<State> = _stateFlow.asStateFlow()

    fun doCloseComments() {
        val currentState = _stateFlow.value
        _stateFlow.value = currentState.copy(sheetState = false)
    }

    fun doOpenComments() {
        val currentState = _stateFlow.value
        _stateFlow.value = currentState.copy(sheetState = true)
    }
}