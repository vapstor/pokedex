package br.com.vapstor.pokedex.domain.usecase

import br.com.vapstor.pokedex.domain.model.PokemonsPage
import br.com.vapstor.pokedex.domain.repository.PokemonRepo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

/**
 *  UseCase para encapsular a lógica de negócio de buscar pokemons
 */
class GetPokemonsPageUseCase @Inject constructor(private val pokemonRepo: PokemonRepo) {
    operator fun invoke(limit: Int, offset: Int): Flow<PokemonsPage> = flow {
        // Lógica de negócio, validações, transformações, etc.
        // Por enquanto, apenas chamaremos o repositório para buscar os dados.
        emitAll(pokemonRepo.getPokemonPage(limit, offset))
    }
}
