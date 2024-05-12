package com.marbjorn.pokeapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailsDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("stats")
    val stats: List<StatWrapperDto>,

    @SerializedName("sprites")
    val sprites: SpritesDto
) {
    data class SpritesDto(
        @SerializedName("front_default")
        val frontDefaultUrl: String,

        @SerializedName("front_shiny")
        val frontShinyUrl: String
    )

    data class StatWrapperDto(
        @SerializedName("base_stat")
        val baseStat: Int,

        @SerializedName("effort")
        val effort: Int,

        @SerializedName("stat")
        val stat: StatDto
    ) {
        data class StatDto(
            @SerializedName("name")
            val name: String
        )
    }
}
