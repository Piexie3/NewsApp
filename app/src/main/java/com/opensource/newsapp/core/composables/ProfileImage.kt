package com.opensource.newsapp.core.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.opensource.newsapp.R
import com.opensource.newsapp.theme.pinkSecodary

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
   image: String?,
    onClicked: ()-> Unit
) {

    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(100.dp)
            .border(
                width = 2.dp,
                color = pinkSecodary,
                shape = CircleShape
            )
            .padding(2.dp)
            .clickable {
                onClicked()
            }

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .placeholder(R.drawable.placeholder)
                .build(),
            contentDescription = "Profile Image",
            modifier = Modifier
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .padding(3.dp)
                .clip(CircleShape),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            fallback = painterResource(id = R.drawable.placeholder)
        )

    }
}
