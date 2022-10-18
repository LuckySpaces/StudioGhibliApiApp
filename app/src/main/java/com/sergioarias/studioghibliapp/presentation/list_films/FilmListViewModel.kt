package com.sergioarias.studioghibliapp.presentation.list_films

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergioarias.studioghibliapp.common.Resource
import com.sergioarias.studioghibliapp.domain.use_case.get_films_use_case.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel@Inject constructor(
    val getFilmsUseCase: GetFilmsUseCase
): ViewModel() {
    private val _state  = mutableStateOf(FilmListState())
    val state: State<FilmListState> = _state

    init {
        getFilms()
    }

    private fun getFilms() {
        getFilmsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = FilmListState(data = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = FilmListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = FilmListState(error = result.message ?: "Unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}