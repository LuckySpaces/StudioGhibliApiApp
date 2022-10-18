package com.sergioarias.studioghibliapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sergioarias.studioghibliapp.presentation.film_detail.FilmDetailScreen
import com.sergioarias.studioghibliapp.presentation.list_films.FilmListScreen
import com.sergioarias.studioghibliapp.presentation.ui.theme.StudioGhibliAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudioGhibliAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.FilmListScreen.route
                ) {
                    composable(Screens.FilmListScreen.route) {
                        FilmListScreen(navController)
                    }
                    composable(Screens.FilmDetailScreen.route+"/{filmId}") {
                        FilmDetailScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StudioGhibliAppTheme {
        Greeting("Android")
    }
}