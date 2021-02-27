package com.codext.project.core.data.source.remote

import android.util.Log
import com.codext.project.core.data.source.remote.network.ApiResponse
import com.codext.project.core.data.source.remote.network.ApiService
import com.codext.project.core.data.source.remote.response.PokemonItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPokemon(limit: Int, offset: Int): Flow<ApiResponse<List<PokemonItemResponse>>> {
        return flow {
            try {
                val response = apiService.getAllPokemon(offset, limit)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.simpleName, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}