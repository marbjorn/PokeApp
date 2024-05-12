package com.marbjorn.pokeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PokemonModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val color: String,
    val stats: Stats,
) : Parcelable {
    @Parcelize
    data class Stats(
        val hp: Int,
        val attack: Int,
        val defense: Int,
        val specialAttack: Int,
        val specialDefense: Int,
        val speed: Int
    ) : Parcelable
}
