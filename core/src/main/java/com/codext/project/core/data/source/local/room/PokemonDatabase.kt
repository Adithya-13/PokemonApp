package com.codext.project.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codext.project.core.data.source.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = true)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}