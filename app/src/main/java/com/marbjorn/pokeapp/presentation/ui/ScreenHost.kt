package com.marbjorn.pokeapp.presentation.ui

import android.net.Uri
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.presentation.Destinations
import com.marbjorn.pokeapp.presentation.PokemonType
import com.marbjorn.pokeapp.presentation.HostViewModel
import com.marbjorn.pokeapp.presentation.ui.screen.PokemonDetailsScreen
import com.marbjorn.pokeapp.presentation.ui.screen.PokemonListScreen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ScreenHost() {
    val navController = rememberNavController()
    val vm = hiltViewModel<HostViewModel>()

    LaunchedEffect(Unit) {
        vm.loadData()
    }
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            startDestination = Destinations.LIST_SCREEN
        ) {
            composable(Destinations.LIST_SCREEN) {
                PokemonListScreen(this, hostViewModel = vm) {
                    val json = Uri.encode(Gson().toJson(it))
                    navController.navigate("${Destinations.DETAILS_SCREEN}/$json")
                }
            }
            composable(
                "${Destinations.DETAILS_SCREEN}/{pokemon}",
                arguments = listOf(navArgument("pokemon") { type = PokemonType() })
            ) {
                val pokemon = it.arguments?.getParcelable<PokemonModel>("pokemon")!!
                PokemonDetailsScreen(this, pokemonModel = pokemon)
            }
        }
    }
}
