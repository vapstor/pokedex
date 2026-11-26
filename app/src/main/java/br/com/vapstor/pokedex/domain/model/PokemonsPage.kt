package br.com.vapstor.pokedex.domain.model

/**
 *  Model para espelhar o que é recebido da API
 */
data class PokemonsPage(
    val items: List<Pokemon>,
    val hasNextPage: Boolean,
    val totalCount: Int
)