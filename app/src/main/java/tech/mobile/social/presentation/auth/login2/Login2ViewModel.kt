package tech.mobile.social.presentation.auth.login2

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.mobile.social.domain.repository.AuthorizeRepo
import javax.inject.Inject

@HiltViewModel
class Login2ViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authorizeRepo: AuthorizeRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<Login2State> = MutableStateFlow(Login2State())

    private val state = _stateFlow.asStateFlow();

    val stateFlow: StateFlow<Login2State> = _stateFlow.asStateFlow()

    fun doLogin() {
        // call api login
        Log.d("Login", "doLogin: " + state.value.email + " " + state.value.password)
        viewModelScope.launch {
//            val access = authorizeRepo.getAuthorize(state.value.email, state.value.password)
//            Log.d("AccessToken", access.toString())
        }
    }

    fun updateEmail(email: String) {
        Log.d("Login", "updateEmail: $email")
        _stateFlow.value = _stateFlow.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        Log.d("Login", "updatePassword: $password")
        _stateFlow.value = _stateFlow.value.copy(password = password)
    }

}