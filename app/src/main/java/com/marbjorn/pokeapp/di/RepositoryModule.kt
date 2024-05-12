package com.marbjorn.pokeapp.di

import com.marbjorn.pokeapp.data.PokemonRepositoryImpl
import com.marbjorn.pokeapp.domain.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindPokemonRepository(pokemonRepository: PokemonRepositoryImpl): PokemonRepository
}
