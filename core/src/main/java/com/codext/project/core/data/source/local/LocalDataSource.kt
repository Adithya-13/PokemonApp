package com.codext.project.core.data.source.local

import com.codext.project.core.data.source.local.entity.PokemonEntity
import com.codext.project.core.data.source.local.room.PokemonDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val pokemonDao: PokemonDao) {

    fun getAllPokemon(): Flow<List<PokemonEntity>> = pokemonDao.getAllPokemon()

    fun getAllFavoritePokemon(): Flow<List<PokemonEntity>> = pokemonDao.getAllFavoritePokemon()

    suspend fun insertPokemon(pokemon: List<PokemonEntity>) = pokemonDao.insertPokemon(pokemon)

    fun setFavoritePokemon(pokemon: PokemonEntity, newState: Boolean) {
        pokemon.isFavorite = newState
        pokemonDao.updateFavoritePokemon(pokemon)
    }

}