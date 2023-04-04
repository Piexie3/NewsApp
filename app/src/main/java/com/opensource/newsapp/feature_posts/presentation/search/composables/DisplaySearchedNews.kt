package com.opensource.newsapp.feature_posts.presentation.search.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.opensource.newsapp.feature_posts.presentation.SharedViewModel
import com.opensource.newsapp.feature_posts.presentation.search.SearchViewModel

@Composable
fun DisplaySearchedNews(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val searchViewModel: SearchViewModel = hiltViewModel()

    val searchNewsState = searchViewModel.searchListState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {

        val args = sharedViewModel.searchedKeyWord

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 5.dp, bottom = 5.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //back arrow
            IconButton(
                modifier = Modifier.padding(end = 7.dp),
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back arrow",
                    modifier = Modifier
                        .size(40.dp),
                    tint = Color.White,
                )
            }

            Text(
                text = "Search news",
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        }

        Column(
            modifier = Modifier.padding(top = 60.dp)
        ) {
            searchNewsState.news?.let {
                Text(
                    text = "Showing results for \"$args\" news",
                    modifier = Modifier
                        .padding(start = 15.dp, end = 16.dp, top = 1.dp, bottom = 7.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }


            LazyColumn(
                contentPadding = PaddingValues(
                    start = 4.dp,
                    end = 4.dp,
                    top = 15.dp,
                    bottom = 70.dp
                ),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {

                searchNewsState.news?.let {
                    items(it) { news ->
                        NewsSectionListItem(
                            searchNewsItem = news,
                            navController = navController,
                            sharedNewsDetailsViewModel = sharedViewModel
                        )

                    }
                }

            }
        }


        // show error if any
        if (searchNewsState.error.isNotBlank()) {
            Text(
                text = searchNewsState.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        //load shimmer
        if (searchNewsState.isLoading) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 60.dp)
            ) {

                Text(
                    text = "Loading results for \"$args\" news",
                    modifier = Modifier
                        .padding(start = 4.dp, end = 16.dp, bottom = 7.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(40.dp),
                    color = Color.Magenta
                )
            }
        }

    }
}