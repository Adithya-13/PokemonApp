package com.codext.project.pokemonapp.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.codext.project.core.data.source.Resource
import com.codext.project.core.ui.PokemonAdapter
import com.codext.project.pokemonapp.R
import com.codext.project.pokemonapp.databinding.ActivityHomeBinding
import com.codext.project.pokemonapp.favorite.FavoriteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonAdapter = PokemonAdapter()
        pokemonAdapter.onItemClick = { pokemon ->
            homeViewModel.setFavoritePokemon(pokemon, !pokemon.isFavorite)
        }

        homeViewModel.getAllPokemon(0, 20).observe(this, { pokemon ->
            if (pokemon != null) {
                when (pokemon) {
                    is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressbar.visibility = View.GONE
                        pokemonAdapter.setData(pokemon.data)
                    }
                    is Resource.Error -> {
                        binding.progressbar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text =
                            pokemon.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(binding.recyclerView) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = pokemonAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favButton -> goToFavoriteActivity()
        }
        return true
    }

    private fun goToFavoriteActivity() {
        val intent = Intent(this, FavoriteActivity::class.java)
        startActivity(intent)
    }
}