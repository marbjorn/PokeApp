package com.marbjorn.pokeapp.presentation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.presentation.toPokemonColor
import com.marbjorn.pokeapp.presentation.toStatMap
import com.marbjorn.pokeapp.presentation.ui.theme.spacing
import kotlin.math.max


@Composable
fun StatsDiagram(pokemonModel: PokemonModel) {
    val stats = remember { pokemonModel.stats.toStatMap() }

    val relativeMaxWidth = remember { stats.values.max() }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        stats.forEach { (label, value) ->
            Column(Modifier.padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            )) {
                Text(
                    text = "$label: $value",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier,
                )
                StatBar(
                    modifier = Modifier.height(MaterialTheme.spacing.small),
                    color = pokemonModel.color.toPokemonColor(),
                    value = value,
                    max(relativeMaxWidth, 100)
                )
            }
        }
    }
}

@Composable
@Preview
fun StatsDiagramPreview() {
    val stubPokemon = remember {
        PokemonModel(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "",
            color = "blue",
            stats = PokemonModel.Stats(
                hp = 10,
                attack = 20,
                defense = 30,
                specialAttack = 40,
                specialDefense = 50,
                speed = 100
            )
        )
    }
    StatsDiagram(stubPokemon)
}

@Composable
fun StatBar(modifier: Modifier, color: Color, value: Int, maxValue: Int) {
    val animateBar = remember {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {
        animateBar.animateTo(
            targetValue = value.toFloat() / maxValue.toFloat(),
            animationSpec = tween(durationMillis = 1500)
        )
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.spacing.extraSmall))
            .background(color)
            .fillMaxWidth(animateBar.value)
    )
}
