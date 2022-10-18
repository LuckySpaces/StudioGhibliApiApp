package com.sergioarias.studioghibliapp.domain.use_case.get_films_use_case

import com.sergioarias.studioghibliapp.common.Resource
import com.sergioarias.studioghibliapp.domain.model.Film
import com.sergioarias.studioghibliapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    operator fun invoke(): Flow<Resource<List<Film>>> = flow {
        try {
            emit(Resource.Loading<List<Film>>())
            val films = repository.getFilms().map { it.toFilm() }
            emit(Resource.Success<List<Film>>(films))
        } catch (e: IOException) {
            emit(Resource.Error<List<Film>>(message = e.localizedMessage ?: "Check internet connection\nand try again"))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Film>>(message = e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}