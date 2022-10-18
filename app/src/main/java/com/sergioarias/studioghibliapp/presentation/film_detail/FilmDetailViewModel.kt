package com.sergioarias.studioghibliapp.presentation.film_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergioarias.studioghibliapp.common.Constants
import com.sergioarias.studioghibliapp.common.Resource
import com.sergioarias.studioghibliapp.domain.use_case.get_film_use_case.GetFilmUseCase
import com.sergioarias.studioghibliapp.presentation.list_films.FilmListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getFilmUseCase: GetFilmUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state  = mutableStateOf(FilmDetailState())
    val state: State<FilmDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_FILM_ID)?. let { filmId ->
            getFilmById(filmId)
        }
    }

    private fun getFilmById(filmId: String) {
        getFilmUseCase(filmId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = FilmDetailState(data = result.data)
                }
                is Resource.Loading -> {
                    _state.value = FilmDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = FilmDetailState(error = result.message ?: "Unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)

    }
}