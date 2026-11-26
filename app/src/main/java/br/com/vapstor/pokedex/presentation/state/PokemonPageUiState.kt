package br.com.vapstor.pokedex.presentation.state

import br.com.vapstor.pokedex.domain.model.Pokemon

data class PokemonPageUiState(
    val pokemons: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasNextPage: Boolean = true,
    val errorMessage: String? = null,
)
