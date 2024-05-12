package com.marbjorn.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesDto(
    @SerializedName("color")
    val color: Color
) {
    data class Color(
        @SerializedName("name")
        val name: String
    )
}
