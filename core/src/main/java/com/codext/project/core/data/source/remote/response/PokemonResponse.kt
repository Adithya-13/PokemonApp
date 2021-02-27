package com.codext.project.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("previous")
	val previous: Any,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<PokemonItemResponse>
)

data class PokemonItemResponse(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
)
