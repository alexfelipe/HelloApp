package br.com.alura.helloapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onUsuarioMudou = {
                    _uiState.value = _uiState.value.copy(
                        usuario = it
                    )
                },
                onSenhaMudou = {
                    _uiState.value = _uiState.value.copy(
                        senha = it
                    )
                },
                onErro = {
                    _uiState.value = _uiState.value.copy(
                        exibirErro = it
                    )
                },
            )
        }
    }

    fun fazerLogin() {
        viewModelScope.launch {
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LoginFactory() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return LoginViewModel() as T
    }
}
