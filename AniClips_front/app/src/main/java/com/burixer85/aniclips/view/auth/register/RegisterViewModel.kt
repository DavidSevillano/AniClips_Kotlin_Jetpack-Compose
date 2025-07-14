package com.burixer85.aniclips.view.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burixer85.aniclips.data.manager.SessionManager
import com.burixer85.aniclips.domain.model.auth.OperationResultAuth
import com.burixer85.aniclips.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val registerUseCase: RegisterUseCase,
    val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    private val _eventChannel = Channel<String>()
    val eventFlow = _eventChannel.receiveAsFlow()

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

    fun setLoadingTrue() {
        _uiState.update { it.copy(isLoading = true) }
    }

    fun setLoadingFalse() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun onClickSelected() {
        viewModelScope.launch(Dispatchers.IO) {
            setLoadingTrue()
            val result = registerUseCase(
                _uiState.value.username,
                _uiState.value.email,
                _uiState.value.password,
                _uiState.value.repeatPassword
            )
            setLoadingFalse()
            when (result) {
                is OperationResultAuth.Success -> {
                    viewModelScope.launch {
                        sessionManager.saveUsername(_uiState.value.username)
                        _eventChannel.send("Registro exitoso")
                    }
                }

                is OperationResultAuth.EmptyFields -> _eventChannel.send("Campos vacíos")
                is OperationResultAuth.InvalidCredentials -> _eventChannel.send("Datos inválidos o usuario ya existente")
                is OperationResultAuth.NetworkError -> _eventChannel.send("Error de red")
                is OperationResultAuth.InvalidEmailFormat -> _eventChannel.send("Formato del email erróneo")
                is OperationResultAuth.RepeatPasswordError -> _eventChannel.send("Las contraseñas no coinciden")
            }
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