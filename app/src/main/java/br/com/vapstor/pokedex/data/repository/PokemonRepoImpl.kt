package br.com.vapstor.pokedex.data.repository

import br.com.vapstor.pokedex.data.mapper.PokemonMapper
import br.com.vapstor.pokedex.data.remote.PokemonAPI
import br.com.vapstor.pokedex.domain.model.PokemonsPage
import br.com.vapstor.pokedex.domain.repository.PokemonRepo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *  Implementação do repositório de pokemons
 */
class PokemonRepoImpl @Inject constructor(private val api: PokemonAPI) : PokemonRepo {

    /**
     *  Retorna uma lista de pokemons a partir da API
     *  com base no limite e no offset fornecidos.
     *  O resultado é emitido como um Flow de lista de página pokemons.
     */
    override suspend fun getPokemonPage(limit: Int, offset: Int): Flow<PokemonsPage> = flow {
        val response = api.getPokemons(limit, offset)
        val page = PokemonsPage(
            items = response.results.map { PokemonMapper.toDomain(it) },
            hasNextPage = response.next != null,
            totalCount = response.count,
        )
        emit(page)
    }
}
