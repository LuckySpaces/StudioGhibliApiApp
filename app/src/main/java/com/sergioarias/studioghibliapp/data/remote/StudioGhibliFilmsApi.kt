package com.sergioarias.studioghibliapp.data.remote

import com.sergioarias.studioghibliapp.data.remote.dto.FilmDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StudioGhibliFilmsApi {
    @GET("/films")
    suspend fun getFilms(): List<FilmDto>
    @GET("/films/{filmId}")
    suspend fun getFilmById(@Path("filmId") filmId: String): FilmDto
}