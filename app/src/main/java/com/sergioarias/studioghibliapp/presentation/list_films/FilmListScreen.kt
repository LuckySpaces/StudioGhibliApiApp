package com.sergioarias.studioghibliapp.presentation.list_films

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sergioarias.studioghibliapp.R
import com.sergioarias.studioghibliapp.presentation.Screens
import com.sergioarias.studioghibliapp.presentation.list_films.components.FilmCard
import com.sergioarias.studioghibliapp.presentation.ui.theme.DarkBlue
import com.sergioarias.studioghibliapp.presentation.ui.theme.LightBlue

@Composable
fun FilmListScreen(
    navController: NavController,
    filmListViewModel: FilmListViewModel = hiltViewModel()
) {
    val state = filmListViewModel.state.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.top_bar_title),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light
                    )
                },
                backgroundColor = LightBlue
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    LazyColumn(contentPadding = it) {
                        items(state.data ?: emptyList()) { film ->
                            FilmCard(film) {
                                navController.navigate(Screens.FilmDetailScreen.route + "/${film.id}")
                            }
                        }
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                if (state.error.isNotEmpty()) {
                    Text(text = state.error, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}