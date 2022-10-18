package com.sergioarias.studioghibliapp.presentation.list_films.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sergioarias.studioghibliapp.R
import com.sergioarias.studioghibliapp.domain.model.Film
import com.sergioarias.studioghibliapp.presentation.ui.theme.LightBlue

@Composable
fun FilmCard(
    film: Film,
    onViewDetailsClicked: () -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            1.dp,
            if (isSystemInDarkTheme()) LightBlue else Color.Transparent
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.DarkGray
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(film.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = film.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onViewDetailsClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.more_details),
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}