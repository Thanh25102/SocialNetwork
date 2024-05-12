package tech.mobile.social.presentation.auth.otp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.mobile.social.domain.Result
import tech.mobile.social.domain.repository.AuthorizeRepo

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val authorizeRepo: AuthorizeRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OtpState> = MutableStateFlow(OtpState())

    val stateFlow: StateFlow<OtpState> = _stateFlow.asStateFlow()

    fun updatePassword(password: String) {
        _stateFlow.value = _stateFlow.value.copy(password = password)
    }

    fun updateOtp(otp: String) {
        _stateFlow.value = _stateFlow.value.copy(otp = otp)
    }

    fun updateEmail(email: String) {
        _stateFlow.value = _stateFlow.value.copy(email = email)
    }

    fun updatePasswordConfirm(passwordConfirm: String) {
        _stateFlow.value = _stateFlow.value.copy(passwordConfirm = passwordConfirm)
    }

    fun resetPassword() {
        val (email, otp, password, passwordConfirm) = stateFlow.value

        if (email == "") {
            _stateFlow.value =
                _stateFlow.value.copy(
                    isError = true,
                    isSuccess = false,
                    erMess = "Email bắt buộc nhập!"
                )
            return
        }

        if (password == "") {
            _stateFlow.value =
                _stateFlow.value.copy(
                    isError = true,
                    isSuccess = false,
                    erMess = "Mật khẩu bắt buộc nhập!"
                )
            return
        }

        if (passwordConfirm == "" || passwordConfirm != password) {
            _stateFlow.value =
                _stateFlow.value.copy(
                    isError = true,
                    isSuccess = false,
                    erMess = "Mật khẩu nhập lại không khớp!"
                )
            return
        }

        viewModelScope.launch {
            when (authorizeRepo.resetPassword(email, otp, password)) {
                is Result.Error -> {
                    _stateFlow.value =
                        _stateFlow.value.copy(
                            isError = true,
                            isSuccess = false,
                            erMess = "Mã xác nhận sai!"
                        )
                }

                is Result.Success -> {
                    _stateFlow.value = _stateFlow.value.copy(isError = false, isSuccess = true)
                }
            }
        }
    }

}