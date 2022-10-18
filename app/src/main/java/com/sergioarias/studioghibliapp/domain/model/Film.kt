package com.sergioarias.studioghibliapp.domain.model

import com.google.gson.annotations.SerializedName

data class Film(
    val id: String = "",
    val title: String = "No title available",
    val originalTitle: String = "No title available",
    val originalTitleRomanised: String = "No title available",
    val image: String = "",
    val movieBanner: String = "",
    val description: String = "No description available",
    val director: String = "No director available",
    val producer: String = "No producer available",
    val releaseDate: String = "No release date available",
    val runningTime: String  = "No runtime available",
    val rtScore: String = "No score available",
    val url: String = ""
)
