package com.codext.project.pokemonapp.favorite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.codext.project.core.ui.PokemonAdapter
import com.codext.project.pokemonapp.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val pokemonAdapter = PokemonAdapter()
        pokemonAdapter.onItemClick = { pokemon ->
            favoriteViewModel.setFavoritePokemon(pokemon, !pokemon.isFavorite)
        }

        favoriteViewModel.getAllFavoritePokemon().observe(this, { pokemon ->
            pokemonAdapter.setData(pokemon)
        })

        with(binding.recyclerViewFavorite) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = pokemonAdapter
        }
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

}