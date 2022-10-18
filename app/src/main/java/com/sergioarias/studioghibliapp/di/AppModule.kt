package com.sergioarias.studioghibliapp.di

import com.sergioarias.studioghibliapp.common.Constants
import com.sergioarias.studioghibliapp.data.remote.StudioGhibliFilmsApi
import com.sergioarias.studioghibliapp.data.repository.FilmRepositoryImpl
import com.sergioarias.studioghibliapp.domain.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFilmsApi(): StudioGhibliFilmsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudioGhibliFilmsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmRepository(api: StudioGhibliFilmsApi): FilmRepository {
        return FilmRepositoryImpl(api)
    }
}