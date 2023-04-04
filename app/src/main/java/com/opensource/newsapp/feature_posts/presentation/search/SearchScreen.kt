package com.opensource.newsapp.feature_posts.presentation.search

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.opensource.newsapp.feature_posts.presentation.SharedViewModel
import com.opensource.newsapp.feature_posts.presentation.search.composables.NewsSectionListItem
import com.opensource.newsapp.navigation.BottomNavItem
import com.opensource.newsapp.navigation.BottomNavMenu
import com.opensource.newsapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            text = "Discover",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Any type of news and article",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Filter"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isSystemInDarkTheme())
                        Color.Black.copy(.24f) else
                        Color.White.copy(.24f)
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(48.dp),
                containerColor = if (isSystemInDarkTheme())
                    Color.Black.copy(.24f) else
                    Color.White.copy(
                        .24f
                    ),
            ) {
                BottomNavMenu(selectedItem = BottomNavItem.SEARCH, navController)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                val context = LocalContext.current
                Box(
                    modifier = Modifier
                        .height(54.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSystemInDarkTheme())
                                Color.White.copy(alpha = .24F) else
                                Color.Black.copy(alpha = .24F)
                        )

                ) {
                    val focusRequester = remember { FocusRequester() }
                    val focusManager = LocalFocusManager.current

                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester = focusRequester),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Search",
                                color = if (isSystemInDarkTheme())
                                    Color.White else
                                    Color.Black,
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = if (isSystemInDarkTheme())
                                Color.Black.copy(alpha = .24F) else
                                Color.White.copy(alpha = .24F),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = if (isSystemInDarkTheme())
                                Color.White else
                                Color.Black
                        ), keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                sharedViewModel.addSearchedKeyWord(searchQuery)
                                if (searchQuery.isNotEmpty()) {
                                    navController.navigate(Screens.DisplaySearchedScreen.route + "/$searchQuery") {
                                        popUpTo(Screens.DisplaySearchedScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "The search query can't be empty",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        ),
                        trailingIcon = {
                            Row {
                                AnimatedVisibility(visible = searchQuery.trim().isNotEmpty()) {
                                    IconButton(
                                        onClick = {
                                            searchQuery = ""
                                        },
                                        colors = IconButtonDefaults.iconButtonColors(
                                            contentColor = if (isSystemInDarkTheme())
                                                Color.White else
                                                Color.Black
                                        )
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = null
                                        )
                                    }
                                }

                                IconButton(
                                    onClick = {
                                        //on Search
                                        sharedViewModel.addSearchedKeyWord(searchQuery)

                                        if (searchQuery.isNotEmpty()) {
                                            navController.navigate(Screens.DisplaySearchedScreen.route + "/$searchQuery") {
                                                popUpTo(Screens.DisplaySearchedScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "The search query can't be empty",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                        focusManager.clearFocus()
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = if (isSystemInDarkTheme())
                                            Color.White else
                                            Color.Black
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )
                }
            }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItem(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {


        val searchViewModel: SearchViewModel = hiltViewModel()

        val searchNewsState = searchViewModel.searchListState.value
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
        // show error if any
        if (searchNewsState.error.isNotBlank()) {
            Text(
                text = searchNewsState.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
    }
}
