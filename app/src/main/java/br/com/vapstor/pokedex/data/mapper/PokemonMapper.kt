package br.com.vapstor.pokedex.data.mapper

import br.com.vapstor.pokedex.data.model.PokemonItemDTO
import br.com.vapstor.pokedex.domain.model.Pokemon

/**
 *  Mapper para converter entre PokemonDTO e Pokemon
 *  e vice-versa
 */
object PokemonMapper {

    fun toDomain(pokemonList: PokemonItemDTO): Pokemon {
        return Pokemon(
            id = pokemonList.url.trimEnd('/').substringAfterLast('/').toIntOrNull()
                ?: 0,
            name = pokemonList.name,
            url = pokemonList.url
        )
    }

    fun toDTO(pokemon: Pokemon): PokemonItemDTO {
        return PokemonItemDTO(
            name = pokemon.name,
            url = pokemon.url
        )
    }
}