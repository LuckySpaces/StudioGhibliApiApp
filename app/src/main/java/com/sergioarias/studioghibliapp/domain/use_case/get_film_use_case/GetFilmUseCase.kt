package com.sergioarias.studioghibliapp.domain.use_case.get_film_use_case

import com.sergioarias.studioghibliapp.common.Resource
import com.sergioarias.studioghibliapp.data.remote.dto.FilmDto
import com.sergioarias.studioghibliapp.data.repository.FilmRepositoryImpl
import com.sergioarias.studioghibliapp.domain.model.Film
import com.sergioarias.studioghibliapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetFilmUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(filmId: String): Flow<Resource<Film>> = flow {
        try {
            emit(Resource.Loading<Film>())
            val film = repository.getFilmById(filmId).toFilm()
            emit(Resource.Success<Film>(film))
        } catch (e: IOException) {
            emit(Resource.Error<Film>(message = e.localizedMessage ?: "Check internet connection\nand try again"))
        } catch (e: HttpException) {
            emit(Resource.Error<Film>(message = e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}