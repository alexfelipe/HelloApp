package br.com.alura.helloapp.ui.form

import androidx.annotation.StringRes
import java.util.*

data class FormularioContatoUiState(
    val id: Long = 0L,
    val nome: String = "",
    val sobrenome: String = "",
    val telefone: String = "",
    val fotoPerfil: String = "",
    val aniversario: Date? = null,
    val mostrarCaixaDialogoImagem: Boolean = false,
    val mostrarCaixaDialogoData: Boolean = false,
    val onNomeMudou: (String) -> Unit = {},
    val onSobrenomeMudou: (String) -> Unit = {},
    val onTelefoneMudou: (String) -> Unit = {},
    val onFotoPerfilMudou: (String) -> Unit = {},
    val onAniversarioMudou: (String) -> Unit = {},
    var textoAniversairo: String = "",
    @StringRes val tituloAppbar: Int? = null
)