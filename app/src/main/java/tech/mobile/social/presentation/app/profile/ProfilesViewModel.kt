package tech.mobile.social.presentation.app.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.navigation
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

    fun onopenFriend() {

    }

    private val _stateFlow: MutableStateFlow<ProfilesState> = MutableStateFlow(
        ProfilesState(
            emptyList()
        )
    )

    val stateFlow: StateFlow<ProfilesState> = _stateFlow.asStateFlow()

}