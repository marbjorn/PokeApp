package com.marbjorn.pokeapp.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.marbjorn.pokeapp.domain.Result
import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.presentation.HostViewModel
import com.marbjorn.pokeapp.presentation.ui.components.PokemonAppBar
import com.marbjorn.pokeapp.presentation.ui.components.PokemonError
import com.marbjorn.pokeapp.presentation.ui.components.PokemonList
import com.marbjorn.pokeapp.presentation.ui.components.PokemonPreloader


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    hostViewModel: HostViewModel,
    onClick: (PokemonModel) -> Unit
) {
    val listResult by hostViewModel.pokemonList.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            PokemonAppBar(viewModel = hostViewModel, modifier = Modifier)
        },
    ) {
        when (listResult) {
            is Result.Success -> PokemonList(
                animatedVisibilityScope,
                (listResult as Result.Success<List<PokemonModel>>).data,
                Modifier
                    .fillMaxSize()
                    .padding(it),
                onClick
            )
            is Result.Loading -> PokemonPreloader(Modifier.fillMaxSize())
            is Result.Error -> PokemonError(
                (listResult as Result.Error).exception,
                Modifier.fillMaxSize()
            ) {
                hostViewModel.loadData()
            }
        }
    }
}
