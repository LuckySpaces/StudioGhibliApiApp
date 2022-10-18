package com.sergioarias.studioghibliapp.presentation

sealed class Screens(val route: String) {
    object FilmListScreen: Screens("film_list_screen")
    object FilmDetailScreen: Screens("film_detail_screen")
}
