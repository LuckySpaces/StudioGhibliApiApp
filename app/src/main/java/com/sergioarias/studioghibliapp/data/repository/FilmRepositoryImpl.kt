package com.sergioarias.studioghibliapp.data.repository

import com.sergioarias.studioghibliapp.data.remote.StudioGhibliFilmsApi
import com.sergioarias.studioghibliapp.data.remote.dto.FilmDto
import com.sergioarias.studioghibliapp.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: StudioGhibliFilmsApi
): FilmRepository {
    override suspend fun getFilms(): List<FilmDto> {
        return api.getFilms()
    }

    override suspend fun getFilmById(filmId: String): FilmDto {
        return api.getFilmById(filmId)
    }

}