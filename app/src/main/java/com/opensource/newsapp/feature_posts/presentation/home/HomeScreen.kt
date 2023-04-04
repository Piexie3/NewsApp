package com.opensource.newsapp.feature_posts.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manubett.news.feature_posts.presentation.home.HomeViewModel
import com.opensource.newsapp.R
import com.opensource.newsapp.feature_posts.domain.model.NewsDetails
import com.opensource.newsapp.feature_posts.domain.model.NewsItem
import com.opensource.newsapp.feature_posts.presentation.SharedViewModel
import com.opensource.newsapp.feature_posts.presentation.search.composables.NewsSectionListItem
import com.opensource.newsapp.navigation.BottomNavItem
import com.opensource.newsapp.navigation.BottomNavMenu
import com.opensource.newsapp.navigation.Screens


@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(48.dp),
                containerColor = if (isSystemInDarkTheme()) Color.Black.copy(.24f) else Color.White.copy(
                    .24f
                ),
            ) {
                BottomNavMenu(selectedItem = BottomNavItem.HOME, navController)
            }
        }
    ) { paddingValues ->
        val viewModel: HomeViewModel = hiltViewModel()
        val state = viewModel.newsListState.value
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = paddingValues.calculateTopPadding()
                )
        ) {
            Text(
                text = "YourNews"
            )
            LazyRow(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                state.news?.let {
                    items(it) { news ->
                        ImageCard(
                            news = news,
                            sharedViewModel = sharedViewModel,
                            navController = navController
                        )
                    }
                }
            }
            Text(
                text = "Recommended",
                fontFamily = FontFamily.Monospace,
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            )
            LazyColumn {
                state.news?.let {
                    items(it) { news ->
                        NewsSectionListItem(
                            searchNewsItem = news,
                            navController = navController,
                            sharedNewsDetailsViewModel = sharedViewModel
                        )
                    }
                }
            }
//            if (state.error.isNotBlank()) {
//                Text(
//                    text = state.error,
//                    color = MaterialTheme.colorScheme.error,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp)
//                        .align(Alignment.Center)
//                )
//            }
//            if (state.isLoading) {
//                CircularProgressIndicator(
//                    modifier = Modifier.align(
//                        Alignment.Center
//                    )
//                )
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(
    news: NewsItem,
    sharedViewModel: SharedViewModel,
    navController: NavController
) {
    val clicked by remember {
        mutableStateOf(true)
    }
    Card(
        modifier = Modifier.wrapContentSize(),
        onClick = {
            val newsDetails = NewsDetails(
                image = news.image,
                title = news.title,
                headline = news.headline,
                time = news.time,
                author = news.author,
                authorsImage = news.authorsImage,
                ratings = news.ratings,
                sourcePublication = news.sourcePublication,
                sectionName = news.sectionName,
                bodyText = news.bodyText,
                trailText = news.trailText,
                body = news.body,
                productionOffice = news.productionOffice,
                lastModified = news.lastModified,
                twitterHandle = news.twitterHandle,
                tagId = news.tagId,
                bio = news.bio,
                resultId = news.resultId,
                fullNames = news.fullNames,
                id = news.id,
                description = news.description,
                lastName = news.lastName,
                firstName = news.firstName
            )
            sharedViewModel.addDetails(newsDetails)

            navController.navigate(route = Screens.DetailScreen.route) {
                popUpTo(Screens.DetailScreen.route) {
                    inclusive = true
                }
            }
        }
    ) {
        Column{
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(news.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large),
                fallback = painterResource(id = R.drawable.placeholder),
            )
            Spacer(modifier = Modifier.height(8.dp))
            news.title?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.Serif,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
    Column{

    }
}