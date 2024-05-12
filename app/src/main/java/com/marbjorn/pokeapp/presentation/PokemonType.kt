package com.marbjorn.pokeapp.presentation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.marbjorn.pokeapp.domain.model.PokemonModel


class PokemonType : NavType<PokemonModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PokemonModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): PokemonModel {
        return Gson().fromJson(value, PokemonModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: PokemonModel) {
        bundle.putParcelable(key, value)
    }
}
