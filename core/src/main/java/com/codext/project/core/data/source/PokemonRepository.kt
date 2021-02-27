package com.codext.project.core.data.source

import com.codext.project.core.data.source.local.LocalDataSource
import com.codext.project.core.data.source.remote.RemoteDataSource
import com.codext.project.core.data.source.remote.network.ApiResponse
import com.codext.project.core.data.source.remote.response.PokemonItemResponse
import com.codext.project.core.domain.model.Pokemon
import com.codext.project.core.domain.repository.IPokemonRepository
import com.codext.project.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IPokemonRepository {

    override fun getAllPokemon(offset: Int, limit: Int): Flow<Resource<List<Pokemon>>> {
        return object : NetworkBoundResource<List<Pokemon>, List<PokemonItemResponse>>() {
            override fun loadFromDB(): Flow<List<Pokemon>> {
                return localDataSource.getAllPokemon().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Pokemon>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<PokemonItemResponse>>> {
                return remoteDataSource.getAllPokemon(limit, offset)
            }

            override suspend fun saveCallResult(data: List<PokemonItemResponse>) {
                val pokemonList = DataMapper.mapResponsesToEntities(data)
                return localDataSource.insertPokemon(pokemonList)
            }
        }.asFlow()
    }

    override fun getAllFavoritePokemon(): Flow<List<Pokemon>> {
        return localDataSource.getAllFavoritePokemon().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoritePokemon(pokemon: Pokemon, state: Boolean) {
        val pokemonEntity = DataMapper.mapDomainToEntity(pokemon)
        localDataSource.setFavoritePokemon(pokemonEntity, state)
    }
}