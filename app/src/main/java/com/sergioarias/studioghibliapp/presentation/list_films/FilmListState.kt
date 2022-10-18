package com.sergioarias.studioghibliapp.presentation.list_films

import com.sergioarias.studioghibliapp.domain.model.Film

data class FilmListState(
    val isLoading: Boolean = false,
    val data: List<Film>? = null,
    val error: String = ""
)
