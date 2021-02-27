package com.codext.project.core.domain.model

data class Pokemon(
    var pokemonId: Int,
    var name: String,
    var imageUrl: String,
    var isFavorite: Boolean = false
)
