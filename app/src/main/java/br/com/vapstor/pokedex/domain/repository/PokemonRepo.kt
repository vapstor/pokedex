package br.com.vapstor.pokedex.domain.repository

import br.com.vapstor.pokedex.domain.model.PokemonsPage
import kotlinx.coroutines.flow.Flow

interface PokemonRepo {
    suspend fun getPokemonPage(limit: Int, offset: Int): Flow<PokemonsPage>
}