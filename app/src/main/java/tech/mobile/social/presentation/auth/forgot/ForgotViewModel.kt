package tech.mobile.social.presentation.auth.forgot

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
class ForgotViewModel @Inject constructor(
    private val authorizeRepo: AuthorizeRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ForgotState> = MutableStateFlow(ForgotState())

    val stateFlow: StateFlow<ForgotState> = _stateFlow.asStateFlow()

    fun updateEmail(email: String) {
        _stateFlow.value = _stateFlow.value.copy(email = email)
    }

    fun resetSucces() {
        _stateFlow.value = _stateFlow.value.copy(isSuccess = false)
    }

    fun forgotPassword() {

        val (email) = stateFlow.value

        if (email == "") {
            _stateFlow.value =
                _stateFlow.value.copy(
                    isError = true,
                    isSuccess = false,
                    erMess = "Email bắt buộc nhập!!"
                )

            return
        }

        viewModelScope.launch {
            when (authorizeRepo.forgot(email)) {
                is Result.Error -> {
                    _stateFlow.value =
                        _stateFlow.value.copy(
                            isError = true,
                            isSuccess = false,
                            erMess = "Email không tồn tại!"
                        )
                }

                is Result.Success -> {
                    _stateFlow.value = _stateFlow.value.copy(isError = false, isSuccess = true)
                }
            }
        }
    }

}