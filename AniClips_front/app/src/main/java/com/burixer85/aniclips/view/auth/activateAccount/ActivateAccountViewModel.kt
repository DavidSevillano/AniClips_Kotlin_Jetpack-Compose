package com.burixer85.aniclips.view.auth.activateAccount

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ActivateAccountViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ActivateAccountUiState())
    val uiState: StateFlow<ActivateAccountUiState> = _uiState

    private val _eventChannel = Channel<String>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun onCodeChanged(code: String) {
        _uiState.update { state ->
            state.copy(code = code)
        }
    }

    fun onClickSelected() {
        return
    }
}

data class ActivateAccountUiState(
    val code: String = ""
)