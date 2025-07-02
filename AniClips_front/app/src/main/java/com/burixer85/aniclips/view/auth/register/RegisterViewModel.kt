package com.burixer85.aniclips.view.auth.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onEmailChanged(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun onUsernameChanged(username: String) {
        _uiState.update { state ->
            state.copy(username = username)
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun onRepeatPasswordChanged(repeatPassword: String) {
        _uiState.update {
            it.copy(repeatPassword = repeatPassword)
        }
    }
}

data class RegisterUiState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isLoading: Boolean = false
)