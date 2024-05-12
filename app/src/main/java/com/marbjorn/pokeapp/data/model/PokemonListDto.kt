package com.marbjorn.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListDto(
    @SerializedName("results")
    val results: List<PokemonItemDto>
) {
    data class PokemonItemDto (
        @SerializedName("name")
        val name: String,

        @SerializedName("url")
        val url: String,
    )
}