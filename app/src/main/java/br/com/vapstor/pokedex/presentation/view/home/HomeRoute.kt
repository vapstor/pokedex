package br.com.vapstor.pokedex.presentation.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.vapstor.pokedex.domain.model.Pokemon
import br.com.vapstor.pokedex.presentation.state.PokemonPageUiState
import br.com.vapstor.pokedex.presentation.view.home.components.PokemonListContent
import br.com.vapstor.pokedex.presentation.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onPokemonClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onPokemonClick = onPokemonClick,
        onLoadMore = { viewModel.getPokemonsPage(isInitialLoad = false) },
        onRetry = { viewModel.getPokemonsPage(isInitialLoad = true) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: PokemonPageUiState,
    onPokemonClick: (Pokemon) -> Unit,
    onLoadMore: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Pokédex") }
            )
        }
    ) { innerPadding ->
        val hasErrorWithoutData = uiState.errorMessage != null && uiState.pokemons.isEmpty()

        if (hasErrorWithoutData) {
            ErrorStateContent(
                message = uiState.errorMessage,
                onRetry = onRetry,
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            PokemonListContent(
                pokemons = uiState.pokemons,
                isLoading = uiState.isLoading,
                isLoadingMore = uiState.isLoadingMore,
                hasNextPage = uiState.hasNextPage,
                onPokemonClick = onPokemonClick,
                onLoadMore = onLoadMore,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun ErrorStateContent(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Tentar novamente")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = PokemonPageUiState(
            pokemons = listOf(
                Pokemon(id = 1, name = "bulbasaur"),
                Pokemon(id = 4, name = "charmander"),
                Pokemon(id = 25, name = "pikachu")
            ),
            hasNextPage = true
        ),
        onPokemonClick = {},
        onLoadMore = {},
        onRetry = {}
    )
}