package com.marbjorn.pokeapp.data

import com.marbjorn.pokeapp.data.model.PokemonDetailsDto
import com.marbjorn.pokeapp.data.model.PokemonListDto
import com.marbjorn.pokeapp.data.model.PokemonSpeciesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("/api/v2/pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonListDto>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Int): Response<PokemonDetailsDto>

    @GET("/api/v2/pokemon-species/{id}")
    suspend fun getPokemonColor(@Path("id") id: Int): Response<PokemonSpeciesDto>
}
