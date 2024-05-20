package tech.mobile.social.shared


import android.annotation.SuppressLint
import android.content.SharedPreferences
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
    private val prefs: SharedPreferences,
    private val authorizeRepo: AuthorizeRepo
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<UserState> = MutableStateFlow(UserState())

    val stateFlow: StateFlow<UserState> = _stateFlow.asStateFlow()

    fun loading() {
        _stateFlow.value.isLoading = true
    }

    fun doLogin(username: String, password: String) {
        _stateFlow.value.isLoading = true

        if (username == "" || password == "") {
            _stateFlow.value = _stateFlow.value.copy(
                errMsg = "Tài khoản và mật khẩu bắt buộc nhập!",
                isLoading = false
            )
            return
        }

        viewModelScope.launch {
            when (val access = authorizeRepo.login(username, password)) {
                is Result.Success -> {
                    _stateFlow.value =
                        UserState(
                            user = access.data.user,
                            isLogin = true,
                            isLoading = false,
                            errMsg = null
                        )
                }

                is Result.Error -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        errMsg = "Tài khoản hoặc mật khẩu sai!",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun doLoginGoogle(fullname: String, email: String) {
        viewModelScope.launch {
            when (
                val result = authorizeRepo.register(
                    fullname = fullname,
                    email = email,
                    password = email
                )
            ) {
                is Result.Error -> {
                    when (val access = authorizeRepo.login(email, email)) {
                        is Result.Success -> {
                            _stateFlow.value =
                                UserState(
                                    user = access.data.user,
                                    isLogin = true,
                                    isLoading = false,
                                    errMsg = null
                                )
                        }

                        is Result.Error -> {
                            _stateFlow.value = _stateFlow.value.copy(
                                errMsg = "Tài khoản hoặc mật khẩu sai!",
                                isLoading = false
                            )
                        }
                    }
                }

                is Result.Success -> {
                    when (val access = authorizeRepo.login(email, email)) {
                        is Result.Success -> {
                            _stateFlow.value =
                                UserState(
                                    user = access.data.user,
                                    isLogin = true,
                                    isLoading = false,
                                    errMsg = null
                                )
                        }

                        is Result.Error -> {
                            _stateFlow.value = _stateFlow.value.copy(
                                errMsg = "Tài khoản hoặc mật khẩu sai!",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun checkLogin() {
        val token = prefs.getString("token", null)
        if (token != null) {
            _stateFlow.value = _stateFlow.value.copy(isLogin = true)
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun doLogout() {
        prefs.edit().remove("token")
        _stateFlow.value =
            UserState(
                user = null,
                isLogin = false,
                isLoading = false,
                errMsg = null
            )
    }

}

data class UserState(
    var user: User? = null,
    var isLogin: Boolean = false,
    var isLoading: Boolean = false,
    var errMsg: String? = null,
)

