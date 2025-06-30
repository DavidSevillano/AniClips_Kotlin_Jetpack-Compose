package com.burixer85.aniclips.view.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChanged(email: String) {
        _uiState.update { state ->
            state.copy(email = email)
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(password = password) // Es lo mismo que dandole un nombre al "it"
        }
    }

}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)