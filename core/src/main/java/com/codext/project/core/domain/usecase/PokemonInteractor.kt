package com.codext.project.core.domain.usecase

import com.codext.project.core.domain.model.Pokemon
import com.codext.project.core.domain.repository.IPokemonRepository
import javax.inject.Inject

class PokemonInteractor @Inject constructor(private val iPokemonRepository: IPokemonRepository) :
    PokemonUseCase {

    override fun getAllPokemon(offset: Int, limit: Int) =
        iPokemonRepository.getAllPokemon(offset, limit)

    override fun getAllFavoritePokemon() = iPokemonRepository.getAllFavoritePokemon()

    override suspend fun setFavoritePokemon(pokemon: Pokemon, state: Boolean) =
        iPokemonRepository.setFavoritePokemon(pokemon, state)

}