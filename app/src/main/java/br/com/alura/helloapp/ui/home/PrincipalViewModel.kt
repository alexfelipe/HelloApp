package br.com.alura.helloapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.helloapp.database.ContatoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PrincipalViewModel(private val contatoDao: ContatoDao) : ViewModel() {

    private val _uiState = MutableStateFlow(PrincipalUiState())
    val uiState: StateFlow<PrincipalUiState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            buscaProdutos()
        }
    }

    private suspend fun buscaProdutos() {
        contatoDao.buscaTodos().collect() {
            _uiState.value = _uiState.value.copy(
                contatos = it
            )
        }
    }
}