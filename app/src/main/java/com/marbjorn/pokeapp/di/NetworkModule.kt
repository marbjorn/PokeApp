package com.marbjorn.pokeapp.di

import com.marbjorn.pokeapp.data.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://pokeapi.co"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providePokemonApi(): PokemonApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
}
