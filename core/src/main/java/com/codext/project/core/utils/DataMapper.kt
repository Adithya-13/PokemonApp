package com.codext.project.core.utils

import com.codext.project.core.data.source.local.entity.PokemonEntity
import com.codext.project.core.data.source.remote.response.PokemonItemResponse
import com.codext.project.core.domain.model.Pokemon

object DataMapper {
    fun mapResponsesToEntities(input: List<PokemonItemResponse>): List<PokemonEntity> {
        val pokemonList = ArrayList<PokemonEntity>()
        input.map {
            val pokemonEntity = PokemonEntity(
                name = it.name,
                imageUrl = it.url,
                isFavorite = false
            )
            pokemonList.add(pokemonEntity)
        }
        return pokemonList
    }

    fun mapEntitiesToDomain(input: List<PokemonEntity>): List<Pokemon> {
        val pokemonList = ArrayList<Pokemon>()
        input.map {
            val pokemon = Pokemon(
                pokemonId = it.pokemonId,
                name = it.name,
                imageUrl = it.imageUrl,
                isFavorite = it.isFavorite
            )
            pokemonList.add(pokemon)
        }
        return pokemonList
    }

    fun mapDomainToEntity(input: Pokemon): PokemonEntity {
        return PokemonEntity(
            pokemonId = input.pokemonId,
            name = input.name,
            imageUrl = input.imageUrl,
            isFavorite = input.isFavorite,
        )
    }
}