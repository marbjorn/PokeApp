package com.marbjorn.pokeapp.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marbjorn.pokeapp.R
import com.marbjorn.pokeapp.presentation.ui.theme.PokeAppTheme
import com.marbjorn.pokeapp.presentation.ui.theme.spacing


@Composable
fun PokemonError(error: Throwable, modifier: Modifier, onRetry: ()->Unit) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = stringResource(R.string.error_title),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = error.message.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
@Preview
private fun PokemonErrorPreview() {
    PokeAppTheme {
        PokemonError(Throwable(), modifier = Modifier.fillMaxSize()) {}
    }
}