package com.codext.project.core.data.source.remote.network

import com.codext.project.core.data.source.remote.response.PokemonResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ) : PokemonResponse

}