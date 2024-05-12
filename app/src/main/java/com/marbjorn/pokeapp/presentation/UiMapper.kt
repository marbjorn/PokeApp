package com.marbjorn.pokeapp.presentation

import androidx.compose.ui.graphics.Color
import com.marbjorn.pokeapp.domain.model.PokemonModel.Stats
import com.marbjorn.pokeapp.presentation.ui.theme.Black
import com.marbjorn.pokeapp.presentation.ui.theme.Blue
import com.marbjorn.pokeapp.presentation.ui.theme.Brown
import com.marbjorn.pokeapp.presentation.ui.theme.Gray
import com.marbjorn.pokeapp.presentation.ui.theme.Green
import com.marbjorn.pokeapp.presentation.ui.theme.Pink
import com.marbjorn.pokeapp.presentation.ui.theme.Purple
import com.marbjorn.pokeapp.presentation.ui.theme.Red
import com.marbjorn.pokeapp.presentation.ui.theme.White
import com.marbjorn.pokeapp.presentation.ui.theme.Yellow


fun String.toPokemonColor(): Color = when (this) {
    "green" -> Green
    "blue" -> Blue
    "red" -> Red
    "yellow" -> Yellow
    "black" -> Black
    "gray" -> Gray
    "white" -> White
    "brown" -> Brown
    "purple" -> Purple
    "pink" -> Pink
    else -> Color.Transparent
}

object StatLabel {
    const val HP = "HP"
    const val ATTACK = "Attack"
    const val DEFENSE = "Defence"
    const val SPECIAL_ATTACK = "Special attack"
    const val SPECIAL_DEFENCE = "Special defence"
    const val SPEED = "Speed"
}

fun Stats.toStatMap(): LinkedHashMap<String, Int> = linkedMapOf(
    StatLabel.HP to this.hp,
    StatLabel.ATTACK to this.attack,
    StatLabel.DEFENSE to this.defense,
    StatLabel.SPECIAL_ATTACK to this.specialAttack,
    StatLabel.SPECIAL_DEFENCE to this.specialDefense,
    StatLabel.SPEED to this.speed
)
