package tech.mobile.social.presentation.auth.register

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
import tech.mobile.social.domain.usecase.interfaces.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,

    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())

    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()

    fun doRegister() {
        val (name, email, password) = stateFlow.value

        viewModelScope.launch {
            when (val result = authUseCase.register(name, password, email)) {
                is Result.Error -> {
                    Log.e("user", result.error.message.toString())
                }

                is Result.Success -> {
                    Log.d("user", result.data.toString())
                }
            }
        }

    }

    fun doNameChange(name: String) {
        _stateFlow.value = _stateFlow.value.copy(name = name)
    }

    fun doEmailChange(email: String) {
        _stateFlow.value = _stateFlow.value.copy(email = email)
    }

    fun doPasswordChange(password: String) {
        _stateFlow.value = _stateFlow.value.copy(password = password)
    }

    fun doPasswordConfirmChange(passwordConfirm: String) {
        _stateFlow.value = _stateFlow.value.copy(passwordConfirm = passwordConfirm)
    }

}