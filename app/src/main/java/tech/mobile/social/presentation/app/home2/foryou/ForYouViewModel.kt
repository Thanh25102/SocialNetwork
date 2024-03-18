package tech.mobile.social.presentation.app.home2.foryou

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ForYouViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ForYouState> = MutableStateFlow(ForYouState())

    val stateFlow: StateFlow<ForYouState> = _stateFlow.asStateFlow()

}