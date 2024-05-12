package com.marbjorn.pokeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marbjorn.pokeapp.domain.model.PokemonModel
import com.marbjorn.pokeapp.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.marbjorn.pokeapp.domain.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update


@HiltViewModel
class HostViewModel @Inject constructor(private val repo: PokemonRepository): ViewModel() {
    private var job: Job? = null

    private val _pokemonList = MutableStateFlow<Result<List<PokemonModel>>>(Result.Loading)
    val pokemonList = _pokemonList.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    val currentPage = _currentPage.asStateFlow()

    private val _isFirstPage = MutableStateFlow(_currentPage.value == 0)
    val isFirstPage = _isFirstPage.asStateFlow()

    private val _isLastPage = MutableStateFlow(_currentPage.value == MAX_PAGE)
    val isLastPage = _isLastPage.asStateFlow()

    fun onNextPage() = _currentPage
        .takeIf { it.value < MAX_PAGE }?.update { it + 1 }
        .also { checkFirstOrLast() }

    fun onPrevPage() = _currentPage
        .takeIf { it.value > 0 }?.update { it - 1 }
        .also { checkFirstOrLast() }

    fun loadData() {
        viewModelScope.launch {
            job?.cancelAndJoin()
            job = viewModelScope.launch {
                repo.getPokemonList(_currentPage.value).collectLatest {
                    _pokemonList.value = it
                }
            }
        }
    }

    private fun checkFirstOrLast() {
        _isFirstPage.value = _currentPage.value == 0
        _isLastPage.value = _currentPage.value == MAX_PAGE
    }

    companion object {
        private const val MAX_PAGE = 45
    }
}
