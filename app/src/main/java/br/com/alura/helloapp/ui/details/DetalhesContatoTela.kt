package br.com.alura.helloapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.alura.helloapp.R
import br.com.alura.helloapp.converteParaString
import br.com.alura.helloapp.ui.components.AsyncImagePerfil

@Composable
fun DetalhesContatoTela(
    modifier: Modifier = Modifier,
    viewModel: DetalhesContatoViewlModel = viewModel(),
    onClickApagar: () -> Unit = {},
    onClickEditar: () -> Unit = {},
    onClickVoltar: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    DetalhesContatoTela(
        modifier = modifier,
        state = state,
        onApagaContato = {
            viewModel.removeContato()
            onClickApagar()
        },
        onClickEditar = onClickEditar,
        onClickVoltar = onClickVoltar
    )
}

@Composable
fun DetalhesContatoTela(
    modifier: Modifier = Modifier,
    state: DetalhesContatoUiState,
    onClickVoltar: () -> Unit = {},
    onClickEditar: () -> Unit = {},
    onApagaContato: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            DetalhesContatoAppBar(
                onClickVoltar = onClickVoltar,
                onClickApagar = onApagaContato,
                onClickEditar = onClickEditar
            )
        },
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImagePerfil(
                urlImagem = state.contato.fotoPerfil,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = state.contato.nome,
                style = MaterialTheme.typography.h5
            )

            Divider(thickness = 1.dp)

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        text = stringResource(R.string.ligar),
                        color = MaterialTheme.colors.primary
                    )
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        text = stringResource(R.string.mensagem),
                        color = MaterialTheme.colors.primary
                    )
                }
            }

            Divider(thickness = 1.dp)

            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 22.dp),
                    text = stringResource(R.string.informacoes),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )

                Text(
                    text = "${state.contato.nome} ${state.contato.sobrenome}",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(R.string.nome_completo),
                    color = Color.Gray,
                    style = MaterialTheme.typography.body2
                )

                Text(
                    text = state.contato.telefone,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = stringResource(id = R.string.telefone),
                    color = Color.Gray,
                    style = MaterialTheme.typography.body2
                )

                state.contato.aniversario?.let {
                    Text(
                        text = it.converteParaString(),
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.aniversario),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}


@Composable
fun DetalhesContatoAppBar(
    onClickVoltar: () -> Unit,
    onClickApagar: () -> Unit,
    onClickEditar: () -> Unit
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = onClickVoltar
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.voltar)
                )
            }
        },
        actions = {
            IconButton(
                onClick = onClickEditar
            ) {
                Icon(
                    Icons.Default.Edit,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.editar)
                )
            }

            IconButton(onClick = onClickApagar) {
                Icon(
                    Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.deletar)
                )
            }
        }
    )
}


@Preview
@Composable
fun DetalhesContatoScreenPreview() {
    DetalhesContatoTela(state = DetalhesContatoUiState())
}