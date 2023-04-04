package com.manubett.news.feature_posts.presentation.home.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TwitterTag(tag: String?) {
    Box(
        modifier= Modifier
            .border(
                width = 1.dp,
                 color = Color.Magenta,
                shape = RoundedCornerShape(60.dp)
            )
            .padding(horizontal = 6.dp)
    ){
        if (tag != null) {
            Text(
                text = tag,
                color = MaterialTheme.colors.primary,
                textAlign= TextAlign.Center,
                style = MaterialTheme.typography.body2
            )
        }
    }
}