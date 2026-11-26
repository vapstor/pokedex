package br.com.vapstor.pokedex.data.remote

import br.com.vapstor.pokedex.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Provê acesso aos endpoints da API
 */
interface PokemonAPI {
    @GET(POKEMON)
    suspend fun getPokemons(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): PokemonListResponse
    // Podendo retornar Response<List<PokemonDTO>>
    // para tratamentos de erros específicos em PokemonRepoImpl
}

const val POKEMON = "pokemon"
