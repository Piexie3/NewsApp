package com.opensource.newsapp.core.composables

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun getCustomisedBrush(): Brush {
    val darkTheme: Boolean = isSystemInDarkTheme()

    val shimmerColorForDarkTheme = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )
    val shimmerColorForLightTheme = listOf(
        MaterialTheme.colorScheme.background.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.6f),
    )


    val transition = rememberInfiniteTransition()

    val transitionAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse,
        )
    )

    return Brush.linearGradient(
        colors = if (!darkTheme) shimmerColorForLightTheme else shimmerColorForDarkTheme,
        start = Offset.Zero,
        end = Offset(x = transitionAnim.value, y = transitionAnim.value)
    )
}


@Composable
fun ShimmerLoadingNewsList() {
    val brush = getCustomisedBrush()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 4.dp, top = 50.dp, end = 4.dp),
        verticalArrangement = Arrangement.Top
    ) {
        //image place holder
        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .width(180.dp)
                .height(125.dp)
                .background(brush)
                .padding(5.dp),
        )
        //2 item Headline placeholder
        Spacer(
            modifier = Modifier
                .padding(top = 7.dp)
                .width(160.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush),
        )
        //3 item publication time placeholder
        Spacer(
            modifier = Modifier
                .padding(top = 6.dp)
                .width(110.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush),
        )
        //4 item author placeholder
        Spacer(
            modifier = Modifier
                .padding(top = 6.dp, bottom = 3.dp)
                .width(95.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush),
        )

    }
}

@Composable
fun ShimmerLoadingBreakingNewsList() {
    val brush = getCustomisedBrush()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 4.dp, top = 50.dp, end = 4.dp),
        verticalArrangement = Arrangement.Top
    ) {
        //image place holder
        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .height(125.dp)
                .background(brush)
                .padding(5.dp),
        )
        //2 item Headline placeholder
        Spacer(
            modifier = Modifier
                .padding(top = 7.dp)
                .fillMaxWidth()
                .height(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush),
        )
        //3 item publication time placeholder
        Spacer(
            modifier = Modifier
                .padding(top = 6.dp)
                .width(110.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush),
        )
        //4 item author placeholder
        Spacer(
            modifier = Modifier
                .padding(top = 6.dp, bottom = 3.dp)
                .width(95.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush),
        )

    }
}


@Composable
fun ShimmerLoadingSectionNewsList() {
    val brush = getCustomisedBrush()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        //image place holder
        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(7.dp))
                .size(height = 96.dp, width = 120.dp)
                .background(brush)
                .padding(5.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 3.dp, end = 4.dp),
            verticalArrangement = Arrangement.Top
        ) {

            //headline placeholder
            Spacer(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxWidth(0.95f)
                    .height(54.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush),
            )
            //time placeholder
            Spacer(
                modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp)
                    .fillMaxWidth(0.85f)
                    .height(17.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush),
            )
            //publication placeholder
            Spacer(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 1.dp, start = 5.dp)
                    .fillMaxWidth(0.8f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush),
            )

        }
    }
}

