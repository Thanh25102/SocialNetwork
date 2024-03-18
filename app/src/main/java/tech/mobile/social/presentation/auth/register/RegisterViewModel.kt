package tech.mobile.social.presentation.auth.register

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())

    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()


    fun doRegister() {
        //calll api
        Log.d("user", stateFlow.value.toString())
    }
}