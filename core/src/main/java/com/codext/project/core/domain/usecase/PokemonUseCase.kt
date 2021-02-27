package com.codext.project.core.domain.usecase

import com.codext.project.core.data.source.Resource
import com.codext.project.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {

    fun getAllPokemon(offset: Int, limit: Int): Flow<Resource<List<Pokemon>>>

    fun getAllFavoritePokemon(): Flow<List<Pokemon>>

    suspend fun setFavoritePokemon(pokemon: Pokemon, state: Boolean)
}