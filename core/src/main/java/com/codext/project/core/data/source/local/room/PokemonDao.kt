package com.codext.project.core.data.source.local.room

import androidx.room.*
import com.codext.project.core.data.source.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonEntity")
    fun getAllPokemon() : Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemonEntity WHERE isFavorite = 1")
    fun getAllFavoritePokemon(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: List<PokemonEntity>)

    @Update
    fun updateFavoritePokemon(pokemon: PokemonEntity)
}