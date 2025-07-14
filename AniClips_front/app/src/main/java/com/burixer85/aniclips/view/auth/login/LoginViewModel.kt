package com.burixer85.aniclips.view.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burixer85.aniclips.data.manager.SessionManager
import com.burixer85.aniclips.domain.model.auth.OperationResultAuth
import com.burixer85.aniclips.domain.usecase.auth.LoginUseCase
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
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _eventChannel = Channel<String>()
    val eventFlow = _eventChannel.receiveAsFlow()


    fun onUsernameChanged(username: String) {
        _uiState.update { state ->
            state.copy(username = username)
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(password = password) // Es lo mismo que dandole un nombre al "it"
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
            val result = loginUseCase(_uiState.value.username, _uiState.value.password)
            setLoadingFalse()
            when (result) {
                is OperationResultAuth.Success -> {
                    val user = result.data
                    sessionManager.saveUserLogin(
                        id = user.id,
                        username = user.username,
                        avatar = user.avatar,
                        role = user.role,
                        token = user.token
                    )
                    _eventChannel.send("Login exitoso")
                    Log.d("LoginViewModel", "Token recibido y guardado: ${user.token}")

                }

                is OperationResultAuth.EmptyFields -> _eventChannel.send("Campos vacíos")
                is OperationResultAuth.InvalidCredentials -> _eventChannel.send("Credenciales incorrectas")
                is OperationResultAuth.NetworkError -> _eventChannel.send("Error de red")
                else -> {}
            }
        }
    }

}

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)