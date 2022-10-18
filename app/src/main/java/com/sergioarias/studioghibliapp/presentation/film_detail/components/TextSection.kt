package com.sergioarias.studioghibliapp.presentation.film_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextSection(heading: String, text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = heading,
        fontSize = 20.sp,
        color = Color.DarkGray,
        modifier = Modifier
            .padding(start = 15.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = text,
        fontSize = 15.sp,
        modifier = Modifier
            .padding(horizontal = 15.dp)
    )
}