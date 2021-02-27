package com.codext.project.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codext.project.core.databinding.ItemPokemonBinding
import com.codext.project.core.domain.model.Pokemon
import com.codext.project.core.utils.AdapterHelper.setDrawableFavorite
import com.codext.project.core.utils.AdapterHelper.urlToImageUrl

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private val pokemonList = ArrayList<Pokemon>()
    var onItemClick: ((Pokemon) -> Unit)? = null

    fun setData(newData: List<Pokemon>?) {
        if (newData == null) return
        pokemonList.clear()
        pokemonList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(pokemon.imageUrl.urlToImageUrl())
                    .into(image)

                name.text = pokemon.name
                favoriteButton.setDrawableFavorite(pokemon.isFavorite)
            }
        }

        init {
            binding.favoriteButton.setOnClickListener {
                onItemClick?.invoke(pokemonList[adapterPosition])
            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(pokemonList[adapterPosition])
            }
        }
    }
}