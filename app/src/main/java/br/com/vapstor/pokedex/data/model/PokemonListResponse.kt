package br.com.vapstor.pokedex.data.model

import kotlinx.serialization.Serializable

/**
 *  Model para espelhar o que é recebido da API
 */
@Serializable
data class PokemonListResponse(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonItemDTO>
)

@Serializable
data class PokemonItemDTO(
    val name: String,
    val url: String
)