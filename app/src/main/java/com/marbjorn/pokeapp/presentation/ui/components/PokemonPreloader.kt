package com.marbjorn.pokeapp.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.marbjorn.pokeapp.R
import com.marbjorn.pokeapp.presentation.ui.theme.size


@Composable
fun PokemonPreloader(modifier: Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.pokeball_bouncing)
    )
    Box(modifier = modifier) {
        LottieAnimation(
            modifier = Modifier
                .align(Alignment.Center)
                .size(MaterialTheme.size.card),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}