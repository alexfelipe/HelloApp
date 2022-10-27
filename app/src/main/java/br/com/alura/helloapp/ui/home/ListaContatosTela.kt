package br.com.alura.helloapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.alura.helloapp.R
import br.com.alura.helloapp.data.Contato
import br.com.alura.helloapp.sampleData.contatosExemplo
import br.com.alura.helloapp.ui.components.AsyncImagePerfil

@Composable
fun ListaContatosTela(
    modifier: Modifier = Modifier,
    viewModel: ListaContatosViewModel = viewModel(),
    onClickDeslogar: () -> Unit,
    onClickAbreDetalhes: (Contato) -> Unit,
    onClickAbreCadastro: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { AppBarListaContatos(onClickDeslogar = onClickDeslogar) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickAbreCadastro() },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.adicionar_novo_contato)
                )
            }
        }) { paddingValues ->

        LazyColumn(modifier.padding(paddingValues)) {
            items(state.contatos) { contato ->
                ContatoItem(contato) {
                    onClickAbreDetalhes(contato)
                }
            }
        }
    }
}

@Composable
fun AppBarListaContatos(onClickDeslogar: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(
                onClick = onClickDeslogar
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.deslogar)
                )
            }
        }
    )
}

@Composable
fun ContatoItem(
    contato: Contato,
    onClick: (Contato) -> Unit
) {
    Card(
        Modifier.clickable { onClick(contato) },
        backgroundColor = Color.White
    ) {
        Row(
            Modifier.padding(16.dp),
        ) {

            AsyncImagePerfil(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                urlImagem = contato.fotoPerfil
            )

            Column(
                Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = contato.nome,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = contato.sobrenome
                )
            }
        }
    }
}

@Preview
@Composable
fun ListaContatosPreview() {
    ListaContatosTela(Modifier, viewModel(), {}, {}, {})
}

@Preview
@Composable
fun ContatoItemPreview() {
    ContatoItem(contatosExemplo.first()) {}
}
