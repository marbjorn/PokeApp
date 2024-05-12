package com.marbjorn.pokeapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Ratio(
    val rect: Float = 0.2f,
    val semiRect: Float = 0.8f,
    val square: Float = 1f
)

val LocalRatio = compositionLocalOf { Ratio() }

val MaterialTheme.ratio: Ratio
    @Composable
    @ReadOnlyComposable
    get() = LocalRatio.current