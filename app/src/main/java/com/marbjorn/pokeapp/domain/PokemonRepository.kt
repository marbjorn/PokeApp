package com.marbjorn.pokeapp.domain

import com.marbjorn.pokeapp.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow


interface PokemonRepository {
    suspend fun getPokemonList(pageNum: Int): Flow<Result<List<PokemonModel>>>
}
