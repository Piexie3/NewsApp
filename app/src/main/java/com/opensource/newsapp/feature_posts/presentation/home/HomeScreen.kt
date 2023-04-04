package com.opensource.newsapp.feature_posts.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.opensource.newsapp.R
import com.opensource.newsapp.core.composables.ProfileImage
import com.opensource.newsapp.feature_posts.domain.model.NewsDetails
import com.opensource.newsapp.feature_posts.domain.model.NewsItem
import com.manubett.news.feature_posts.presentation.home.HomeViewModel
import com.opensource.newsapp.feature_posts.presentation.SharedViewModel
import com.opensource.newsapp.navigation.BottomNavItem
import com.opensource.newsapp.navigation.BottomNavMenu
import com.opensource.newsapp.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ViewHeadline,
                            contentDescription = "Headlines"
                        )
                    }
                },
                title = {
                    Text(text = "News of the day")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.NotificationsActive,
                            contentDescription = "Notification"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),

                )
//                        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
//                modifier = Modifier
//                    .wrapContentHeight()
//                    .clip(CircleShape),
//                contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            )

        },
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
        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                        top = paddingValues.calculateTopPadding()
                    )
            ) {
                if (state.news?.isNotEmpty() == true) {
                    items(state.news) { news ->
                        ImageCard(
                            news = news,
                            sharedViewModel = sharedViewModel,
                            navController = navController
                        )
                    }
                }


            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}

@Composable
fun ImageCard(
    news: NewsItem,
    sharedViewModel: SharedViewModel,
    navController: NavController
) {
    val clicked by remember {
        mutableStateOf(true)
    }
    Column() {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier.width(48.dp)
                    ) {
                        news.authorsImage?.forEach { authorsImage ->
                            ProfileImage(image = authorsImage, modifier = Modifier.size(48.dp)) {
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

                                navController.navigate(route = Screens.AuthorsDetailsScreen.route) {
                                    popUpTo(Screens.AuthorsDetailsScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    news.author?.forEach {
                        Text(
                            text = it,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }


                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "~ ${news.time}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "more",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )
                    }
                }
            }
        }
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
                .clickable {
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
                .clip(MaterialTheme.shapes.large),
            fallback = painterResource(id = R.drawable.placeholder),
        )

    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        news.title?.let {
            Text(
                text = it,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.titleSmall,
                fontFamily = FontFamily.Serif,
                color = Color.Cyan,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(6.dp))
    }
}


