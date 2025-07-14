package com.burixer85.aniclips.view.auth.activateAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burixer85.aniclips.data.manager.SessionManager
import com.burixer85.aniclips.domain.model.auth.OperationResultAuth
import com.burixer85.aniclips.domain.usecase.auth.ActivateAccountUseCase
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
class ActivateAccountViewModel @Inject constructor(
    var activateAccountUseCase: ActivateAccountUseCase,
    sessionManager: SessionManager
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(ActivateAccountUiState())
    val uiState: StateFlow<ActivateAccountUiState> = _uiState

    private val _eventChannel = Channel<String>()
    val eventFlow = _eventChannel.receiveAsFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    init {
        viewModelScope.launch {
            _username.value = sessionManager.getUsername()
        }
    }

    fun onCodeChanged(code: String) {
        _uiState.update { state ->
            state.copy(code = code)
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
            val result = activateAccountUseCase(_uiState.value.code)
            setLoadingFalse()
            when (result) {
                is OperationResultAuth.Success -> _eventChannel.send("Cuenta activada correctamente")
                is OperationResultAuth.EmptyFields -> _eventChannel.send("Campos vacíos")
                is OperationResultAuth.InvalidCredentials -> _eventChannel.send("Credenciales incorrectas")
                is OperationResultAuth.NetworkError -> _eventChannel.send("Error de red")
                else -> {}
            }
        }
    }
}

data class ActivateAccountUiState(
    val code: String = "",
    val isLoading: Boolean = false
)