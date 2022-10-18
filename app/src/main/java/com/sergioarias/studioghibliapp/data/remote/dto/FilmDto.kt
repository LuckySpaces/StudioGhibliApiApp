package com.sergioarias.studioghibliapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sergioarias.studioghibliapp.domain.model.Film

data class FilmDto(
    val id: String,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_title_romanised")
    val originalTitleRomanised: String,
    val image: String,
    @SerializedName("movie_banner")
    val movieBanner: String,
    val description: String,
    val director: String,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("running_time")
    val runningTime: String,
    @SerializedName("rt_score")
    val rtScore: String,
    val people: List<String>,
    val species: List<String>,
    val locations: List<String>,
    val vehicles: List<String>,
    val url: String
) {
    fun toFilm(): Film {
        return Film(
            id = id,
            title = title,
            originalTitle = originalTitle,
            originalTitleRomanised = originalTitleRomanised,
            image = image,
            movieBanner = movieBanner,
            description = description,
            director = director,
            producer = producer,
            releaseDate = releaseDate,
            runningTime = runningTime,
            rtScore = rtScore,
            url = url
        )
    }
}