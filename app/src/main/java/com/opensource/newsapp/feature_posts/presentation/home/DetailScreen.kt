package com.opensource.newsapp.feature_posts.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.opensource.newsapp.R
import com.opensource.newsapp.feature_posts.domain.model.NewsDetails
import com.opensource.newsapp.feature_posts.presentation.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Article Detail"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.BookmarkAdd,
                            contentDescription = "Add Bookmark"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        val state = sharedViewModel.details
        Column(
            modifier = Modifier
                .padding(4.dp)
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Details(state)
        }
    }
}

@Composable
fun Details(
    news: NewsDetails?
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(news?.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop,
                    fallback = painterResource(id = R.drawable.placeholder)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, top = 70.dp)
                        .align(
                            Alignment.BottomStart
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 3.dp, bottom = 10.dp)
                            .clip(RoundedCornerShape(45))
                    ) {
                        news?.sectionName?.let {
                            Text(
                                text = it,//"Health",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                color = Color.White
                            )
                        }
                    }
                    news?.headline?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(bottom = 5.dp, end = 5.dp)
                                .fillMaxWidth(0.9f),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Cyan,
                            maxLines = 3,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }

            }


            Column(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(top = 10.dp, bottom = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    //author
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(35.dp))
                            .background(
                                MaterialTheme.colorScheme.background
                            )
                            .fillMaxWidth(.45f)
                            .padding(start = 3.dp, top = 3.dp, bottom = 3.dp, end = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        news?.authorsImage?.forEach {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(R.drawable.placeholder),
                                contentDescription = "Author image",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .size(30.dp),
                                contentScale = ContentScale.FillBounds,
                                fallback = painterResource(id = R.drawable.placeholder)
                            )
                        }
                        news?.author?.forEach { author ->
                            Text(
                                text = author,//"Fahad Yassin",
                                style = MaterialTheme.typography.titleSmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(start = 6.dp),
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }


                    }

                    //publication time
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(45))
                            .padding(horizontal = 6.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center,
                    ) {

                        news?.lastModified?.let {
                            Text(
                                text = it, //"5 hours ago",
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }


                    }

                    //production office
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(45))
                            .background(
                                MaterialTheme.colorScheme.background
                            )
                            .padding(horizontal = 6.dp, vertical = 10.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        news?.productionOffice?.let {
                            Text(
                                text = it,  //5 ratings",
                                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }


                    }
                }


                //the whole news
                news?.bodyText?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .padding(bottom = 10.dp)
                            .alpha(0.9f),
                        textAlign = TextAlign.Start,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    )
                }
            }

        }
    }
}

