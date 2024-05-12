package com.marbjorn.pokeapp.presentation.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.presentation.ui.theme.ratio
import com.marbjorn.pokeapp.presentation.ui.theme.size
import com.marbjorn.pokeapp.presentation.ui.theme.spacing


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonList(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonList: List<PokemonModel>,
    modifier: Modifier,
    onClick: (PokemonModel) -> Unit
) {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(MaterialTheme.size.card),
        contentPadding = PaddingValues(MaterialTheme.spacing.medium),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        state = state,
    ) {
        items(pokemonList, key = { it.id }) {
            PokemonListItem(
                animatedVisibilityScope,
                pokemonModel = it,
                Modifier
                    .aspectRatio(MaterialTheme.ratio.semiRect)
                    .fillMaxHeight()
                    .clickable { onClick(it) })
        }
    }
}