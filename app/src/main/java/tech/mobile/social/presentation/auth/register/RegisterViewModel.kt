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

    private val _stateFlow = MutableStateFlow(RegisterState())

    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()


    fun doRegister() {
        val (fullname, email, password) = stateFlow.value
        viewModelScope.launch {
            when (val result = authUseCase.register(fullname, password, email)) {
                is Result.Error -> {
                    _stateFlow.value = _stateFlow.value.copy(isError = true, isSuccess = false)
                }

                is Result.Success -> {
                    _stateFlow.value = _stateFlow.value.copy(isError = false, isSuccess = true)
                }
            }
        }

    }

    fun doNameChange(fullname: String) {
        _stateFlow.value = _stateFlow.value.copy(fullname = fullname)
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