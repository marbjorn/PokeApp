package com.marbjorn.pokeapp.presentation.ui.screen

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.presentation.toPokemonColor
import com.marbjorn.pokeapp.presentation.ui.components.StatsDiagram
import com.marbjorn.pokeapp.presentation.ui.theme.ratio
import com.marbjorn.pokeapp.presentation.ui.theme.spacing


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonDetailsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonModel: PokemonModel,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val gradientList = remember {
        listOf(
            pokemonModel.color.toPokemonColor(),
            pokemonModel.color
                .toPokemonColor()
                .copy(alpha = 0.5f)
        )
    }
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier
                    .fillMaxSize()
                    .background(Brush.horizontalGradient(gradientList))
            ) {
                PokemonDetails(
                    pokemonModel = pokemonModel,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        }
        else -> {
            Column(
                modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(gradientList))
            ) {
                PokemonDetails(
                    pokemonModel = pokemonModel,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonDetails(
    pokemonModel: PokemonModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val paddingValues = WindowInsets.navigationBars.asPaddingValues()

    AsyncImage(
        model = pokemonModel.imageUrl,
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .aspectRatio(MaterialTheme.ratio.square)
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .sharedElement(
                state = rememberSharedContentState(key = pokemonModel.imageUrl),
                animatedVisibilityScope = animatedVisibilityScope
            ),
        filterQuality = FilterQuality.None,
        contentScale = ContentScale.Fit,
        contentDescription = null,
        imageLoader = LocalContext.current.imageLoader,
    )

    Card(
        elevation = CardDefaults.cardElevation(MaterialTheme.spacing.small),
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .padding(paddingValues),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = pokemonModel.name,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelLarge
        )

        StatsDiagram(pokemonModel = pokemonModel)
    }
}
