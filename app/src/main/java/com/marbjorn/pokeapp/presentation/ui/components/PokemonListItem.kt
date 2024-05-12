package com.marbjorn.pokeapp.presentation.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.presentation.toPokemonColor
import com.marbjorn.pokeapp.presentation.ui.theme.spacing


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonListItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonModel: PokemonModel,
    modifier: Modifier
) {
    Card(
        colors = CardDefaults.cardColors(pokemonModel.color.toPokemonColor()),
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemonModel.imageUrl)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                .sharedElement(
                    state = rememberSharedContentState(key = pokemonModel.imageUrl),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            filterQuality = FilterQuality.None,
            imageLoader = LocalContext.current.imageLoader
        )

        Text(
            text = pokemonModel.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.White,

            style = MaterialTheme.typography.bodyLarge.copy(
                shadow =
                Shadow(
                    color = Color.Black,
                    offset = Offset(2f, 3f)
                )
            )
        )
    }
}