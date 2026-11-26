package br.com.vapstor.pokedex.presentation.view.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.vapstor.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun PokemonListContent(
    pokemons: List<Pokemon>,
    isLoading: Boolean,
    isLoadingMore: Boolean,
    hasNextPage: Boolean,
    onPokemonClick: (Pokemon) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    // Dispara onLoadMore quando o usuário chega perto do final da lista (ex.: faltando 3 itens)
    LaunchedEffect(listState, pokemons.size, hasNextPage, isLoadingMore) {
        snapshotFlow {
            val totalItems = listState.layoutInfo.totalItemsCount
            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            totalItems > 0 && hasNextPage && !isLoadingMore && lastVisibleIndex >= totalItems - 3
        }
            .distinctUntilChanged()
            .collect { shouldLoadMore ->
                if (shouldLoadMore) {
                    onLoadMore()
                }
            }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading && pokemons.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                itemsIndexed(
                    items = pokemons,
                    key = { _, pokemon -> pokemon.id },
                ) { _, pokemon ->
                    PokemonItemCard(
                        pokemon = pokemon,
                        onClick = onPokemonClick,
                    )
                }

                if (isLoadingMore) {
                    item(key = "loading_more") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonListContentPreview() {
    val samplePokemons = listOf(
        Pokemon(id = 1, name = "bulbasaur"),
        Pokemon(id = 4, name = "charmander"),
        Pokemon(id = 7, name = "squirtle"),
        Pokemon(id = 25, name = "pikachu"),
    )

    PokemonListContent(
        pokemons = samplePokemons,
        isLoading = false,
        isLoadingMore = true,
        hasNextPage = true,
        onPokemonClick = {},
        onLoadMore = {},
    )
}
