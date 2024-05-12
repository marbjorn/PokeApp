package com.marbjorn.pokeapp.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.marbjorn.pokeapp.R
import com.marbjorn.pokeapp.presentation.HostViewModel
import com.marbjorn.pokeapp.presentation.ui.theme.size
import com.marbjorn.pokeapp.presentation.ui.theme.spacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonAppBar(viewModel: HostViewModel, modifier: Modifier) {
    val isFirstPage by viewModel.isFirstPage.collectAsState()
    val isLastPage by viewModel.isLastPage.collectAsState()
    val currentPage by viewModel.currentPage.collectAsState()
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.labelLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary),
        modifier = modifier,
        actions = {
            AnimatedVisibility(visible = !isFirstPage) {
                IconButton(onClick = {
                    viewModel.onPrevPage()
                    viewModel.loadData()
                }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(MaterialTheme.size.icon),
                        painter = painterResource(id = R.drawable.previous),
                        contentDescription = stringResource(id = R.string.previous)
                    )
                }
            }
            Text(text = "${currentPage + 1}")
            AnimatedVisibility(visible = !isLastPage) {
                IconButton(onClick = {
                    viewModel.onNextPage()
                    viewModel.loadData()
                }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(MaterialTheme.size.icon),
                        painter = painterResource(id = R.drawable.next),
                        contentDescription = stringResource(id = R.string.next)
                    )
                }
            }
        })
}

@Preview
@Composable
private fun PokemonAppBarPreview() {
    PokemonAppBar(viewModel = hiltViewModel(), modifier = Modifier)
}