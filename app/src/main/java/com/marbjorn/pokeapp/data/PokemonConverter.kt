package com.marbjorn.pokeapp.data

import com.marbjorn.pokeapp.data.model.PokemonDetailsDto
import com.marbjorn.pokeapp.data.model.PokemonSpeciesDto
import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.domain.model.PokemonModel.Stats

fun pokemonItemToModel(details: PokemonDetailsDto, species: PokemonSpeciesDto): PokemonModel =
    with(details) {
        PokemonModel(
            id,
            name.replaceFirstChar { it.uppercase() },
            sprites.frontDefaultUrl,
            species.color.name,
            Stats(
                hp = stats[0].baseStat,
                attack = stats[1].baseStat,
                defense = stats[2].baseStat,
                specialAttack = stats[3].baseStat,
                specialDefense = stats[4].baseStat,
                speed = stats[5].baseStat,
            )
        )
    }
