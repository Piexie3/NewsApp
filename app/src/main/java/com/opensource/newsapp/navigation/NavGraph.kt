package com.opensource.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.opensource.newsapp.feature_posts.presentation.home.DetailScreen
import com.opensource.newsapp.feature_posts.presentation.home.HomeScreen
import com.opensource.newsapp.feature_posts.presentation.profile.AuthorsProfileScreen
import com.opensource.newsapp.feature_posts.presentation.profile.ProfileScreen
import com.opensource.newsapp.feature_posts.presentation.search.SearchScreen
import com.opensource.newsapp.feature_posts.presentation.search.composables.DisplaySearchedNews
import com.manubett.news.feature_posts.presentation.trending.TrendingScreen
import com.opensource.newsapp.feature_posts.presentation.SharedViewModel
import com.opensource.newsapp.feature_posts.presentation.bookmark.BookMarkScreen

@Composable
fun NavGraph(
    navHostController: NavHostController = rememberNavController(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(navHostController, sharedViewModel)
        }
        composable(
            route = Screens.DetailScreen.route
        ) {
            DetailScreen(navHostController, sharedViewModel)
        }
        composable(Screens.SearchScreen.route) {
            SearchScreen(navHostController, sharedViewModel)
        }
        composable(Screens.TrendsScreen.route) {
            TrendingScreen(navHostController)
        }
        composable(Screens.ProfileScreen.route) {
            ProfileScreen(navHostController)
        }
        composable(Screens.BookMarkScreen.route) {
            BookMarkScreen(navHostController)
        }

        composable(Screens.AuthorsDetailsScreen.route) {
            AuthorsProfileScreen(navHostController, sharedViewModel)
        }
        composable(Screens.DisplaySearchedScreen.route + "/{q}") {
            DisplaySearchedNews(navHostController, sharedViewModel)
        }
    }
}
