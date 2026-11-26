package br.com.vapstor.pokedex.domain.model

/**
 *  Model para espelhar o que é recebido da API
 */
data class Pokemon(
    val id: Int = 0,
    val name: String = "",
    val url: String = "",
)
