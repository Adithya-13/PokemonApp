package com.codext.project.pokemonapp.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codext.project.core.domain.model.Pokemon
import com.codext.project.core.domain.usecase.PokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val pokemonUseCase: PokemonUseCase) :
    ViewModel() {
    fun getAllPokemon(offset: Int, limit: Int) =
        pokemonUseCase.getAllPokemon(offset, limit).asLiveData()

    fun setFavoritePokemon(pokemon: Pokemon, newStatus: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonUseCase.setFavoritePokemon(pokemon, newStatus)
        }
    }
}