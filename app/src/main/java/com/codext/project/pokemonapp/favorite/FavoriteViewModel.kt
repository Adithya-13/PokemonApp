package com.codext.project.pokemonapp.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codext.project.core.domain.model.Pokemon
import com.codext.project.core.domain.usecase.PokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(private val pokemonUseCase: PokemonUseCase) :
    ViewModel() {
    fun getAllFavoritePokemon() = pokemonUseCase.getAllFavoritePokemon().asLiveData()

    fun setFavoritePokemon(pokemon: Pokemon, newStatus: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonUseCase.setFavoritePokemon(pokemon, newStatus)
        }
    }
}