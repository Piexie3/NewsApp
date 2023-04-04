package com.manubett.news.feature_posts.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manubett.news.core.util.Resource
import com.opensource.newsapp.feature_posts.domain.use_cases.GetNewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCases: GetNewsUseCases,
) : ViewModel() {
    private val _newsListState = mutableStateOf(NewsListState())
    val newsListState: State<NewsListState> = _newsListState

    init {
        getArticles()
    }

      private fun getArticles() {

        getNewsUseCases().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _newsListState.value = NewsListState(news = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _newsListState.value = NewsListState(isLoading = true)
                }
                is Resource.Error -> {
                    _newsListState.value =
                        NewsListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }


}