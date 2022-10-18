package com.sergioarias.studioghibliapp.presentation.film_detail

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.sergioarias.studioghibliapp.R
import com.sergioarias.studioghibliapp.domain.model.Film
import com.sergioarias.studioghibliapp.presentation.film_detail.components.TextSection
import com.sergioarias.studioghibliapp.presentation.ui.theme.Gold
import com.sergioarias.studioghibliapp.presentation.ui.theme.LightBlue
import javax.inject.Inject

@Composable
fun FilmDetailScreen(
    navController: NavController,
    viewModel: FilmDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val film = state.data ?: Film()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    //Movie title on top bar
                        Text(
                            text = film.title,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                },
                navigationIcon = {
                    //Back arrow to navigate back
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                },
                backgroundColor = LightBlue
            )
        },
        content = {
            it
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                //Check if the data is being loaded
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                //Check if there was an error
                else if (state.error.isNotEmpty()) {
                    Text(text = state.error, modifier = Modifier.align(Alignment.Center))
                } else {
                    //Display information if retrieval was successful
                    LazyColumn(
                    ) {
                        item {
                            //Movie Banner
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight(.35f)
                                    .fillMaxWidth()
                            ) {
                                Log.d("Movie Banner", film.movieBanner)
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(film.movieBanner)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            //Movie Cover and Description
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(150.dp)
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(film.image)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Text(
                                    text = film.description,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .weight(1f)
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                            //Rating and Runtime
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                //Rating
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Rating",
                                        tint = Gold
                                    )
                                    Text(
                                        text = film.rtScore,
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                                //Runtime
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                                        contentDescription = "Rating",
                                    )
                                    Text(
                                        text = "${film.runningTime}min",
                                        modifier = Modifier
                                            .padding(0.dp),
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                            }
                            Divider(
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                            //Director
                            TextSection(heading = "Directed by", text = film.director)
                            Divider(
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                            //Producer
                            TextSection(heading = "Produced by", text = film.producer)
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                    }
                }
            }
        }
    )
    

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScreenPreview() {
    FilmDetailScreen(navController = rememberNavController())
}