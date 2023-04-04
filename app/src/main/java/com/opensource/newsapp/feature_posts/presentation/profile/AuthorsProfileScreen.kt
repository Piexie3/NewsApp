package com.opensource.newsapp.feature_posts.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.manubett.news.R
import com.opensource.newsapp.core.composables.ProfileImage
import com.opensource.newsapp.feature_posts.domain.model.NewsDetails
import com.opensource.newsapp.feature_posts.presentation.SharedViewModel


@Composable
fun AuthorsProfileScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    Scaffold() { paddingValues ->
        val state = sharedViewModel.details
        Column(
            modifier = Modifier
                .padding(4.dp)
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            AuthorsDetails(state, navController)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthorsDetails(
    news: NewsDetails?,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        ) {


            news?.authorsImage?.forEach { image ->
                news.authorsImage.size.let { it ->
                    HorizontalPager(count = it) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(durationMillis = 250)
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.ic_broken_image)
                                .fallback(R.drawable.placeholder)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background,
                            ),
                            startY = 210f
                        )
                    )
            )
            LazyRow(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomStart)
                    .padding(start = 8.dp, bottom = 2.dp)
            ) {
                item {
                    Box {
                        Column(verticalArrangement = Arrangement.Center) {
                            news?.authorsImage?.forEach {
                                ProfileImage(image = it, modifier = Modifier.size(50.dp)) {

                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            news?.firstName?.forEach { firstName ->
                                news.lastName?.forEach { lastName ->
                                    Text(
                                        text = "$firstName $lastName",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Light
                                    )
                                }
                            }
                        }
                    }
                }

            }
            Box(modifier = Modifier.clip(RoundedCornerShape(100))) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (isSystemInDarkTheme())
                            Color.Black.copy(.5f) else
                            Color.White.copy(.5f),
                        contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                    ),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clip(CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            }
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp)
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(CircleShape)
                        .wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSystemInDarkTheme())
                            Color.White.copy(.24f) else
                            Color.Black.copy(.24f),
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Follow",
                        color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    )
                }
            }
        }
//        Column(
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier.padding(horizontal = 10.dp)
//        ) {
//            news?.description?.forEach { description->
//                Text(
//                    text = description,
//                )
//            }
//            news?.bio?.forEach { bio ->
//                Text(
//                    text = bio,
//                )
//            }
//        }
    }

}




