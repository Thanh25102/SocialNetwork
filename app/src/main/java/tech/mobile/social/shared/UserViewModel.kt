package tech.mobile.social.shared


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.model.auth.User
import tech.mobile.social.domain.repository.AuthorizeRepo
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authorizeRepo: AuthorizeRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<UserState> = MutableStateFlow(UserState())

    val stateFlow: StateFlow<UserState> = _stateFlow.asStateFlow()

    fun doLogin(username: String, password: String) {
        viewModelScope.launch {
            _stateFlow.value.isLoading = true
            when (val access = authorizeRepo.getAuthorize(username, password)) {
                is Result.Success -> {
                    _stateFlow.value =
                        UserState(user = access.data.user, isLogin = true, isLoading = false, errMsg = null)
                }

                is Result.Error -> {
                    Log.i("STATE", "err msg" + access.error.message[0])
                    _stateFlow.value.isLoading = false
                    _stateFlow.value = _stateFlow.value.copy(errMsg = access.error.message[0])
                }
            }
        }
    }

}

data class UserState(
    var user: User? = null,
    var isLogin: Boolean = false,
    var isLoading: Boolean = false,
    var errMsg: String? = null,
)

