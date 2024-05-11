package tech.mobile.social.presentation.auth.login

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())

    private val state = _stateFlow.asStateFlow();

    val stateFlow: StateFlow<LoginState> = _stateFlow.asStateFlow()

    fun updateEmail(email: String) {
        Log.d("Login", "updateEmail: $email")
        _stateFlow.value = _stateFlow.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        Log.d("Login", "updatePassword: $password")
        _stateFlow.value = _stateFlow.value.copy(password = password)
    }


}