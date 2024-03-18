package tech.mobile.social.presentation.test

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class TestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<TestState> = MutableStateFlow(TestState())

    val stateFlow: StateFlow<TestState> = _stateFlow.asStateFlow()

    fun doStuff() {
        // TODO Handle UI Action
    }
}