package com.marbjorn.pokeapp.data

import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.domain.PokemonRepository
import com.marbjorn.pokeapp.domain.Result
import com.marbjorn.pokeapp.domain.asResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject


class PokemonRepositoryImpl @Inject constructor(private val api: PokemonApi) : PokemonRepository {
    private val scope = CoroutineScope(Dispatchers.IO)

    override suspend fun getPokemonList(pageNum: Int): Flow<Result<List<PokemonModel>>> = flow {
        val pokemonList = api.getPokemonList(pageNum * PAGE_SIZE, PAGE_SIZE)
        val deferredPokemonList = pokemonList.body()!!.results.map {
            val id = getIdFromUrl(it.url)
            scope.async(SupervisorJob()) {
                val details = api.getPokemonDetails(id)
                val species = api.getPokemonColor(id)
                pokemonItemToModel(details.body()!!, species.body()!!)
            }
        }
        withTimeout(TIMEOUT) {
            awaitAll(*deferredPokemonList.toTypedArray())
        }
        emit(deferredPokemonList.map { it.getCompleted() })
    }.asResult()

    private fun getIdFromUrl(url: String): Int = url.split('/').last { it.isNotEmpty() }.toInt()

    companion object {
        private const val TAG = "PokemonRepositoryImpl"
        private const val TIMEOUT = 10_000L
        const val PAGE_SIZE = 30
    }
}
