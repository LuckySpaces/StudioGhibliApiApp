package com.sergioarias.studioghibliapp.domain.repository

import com.sergioarias.studioghibliapp.data.remote.dto.FilmDto

interface FilmRepository {
    suspend fun getFilms(): List<FilmDto>
    suspend fun getFilmById(filmId: String): FilmDto
}