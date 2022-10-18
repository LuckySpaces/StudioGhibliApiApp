package com.sergioarias.studioghibliapp.presentation.film_detail

import com.sergioarias.studioghibliapp.domain.model.Film

data class FilmDetailState(
    val isLoading: Boolean = false,
    val data: Film? = null,
    val error: String = ""
)