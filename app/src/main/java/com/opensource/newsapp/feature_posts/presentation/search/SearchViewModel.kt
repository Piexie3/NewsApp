package com.opensource.newsapp.feature_posts.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manubett.news.core.util.Constants.SEARCH_KEYWORD
import com.manubett.news.core.util.Resource
import com.opensource.newsapp.feature_posts.domain.use_cases.SearchUseCase
import com.manubett.news.feature_posts.presentation.home.NewsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _newsListState = mutableStateOf(NewsListState())
    val searchListState: State<NewsListState> = _newsListState

    init {
        savedStateHandle.get<String>(SEARCH_KEYWORD)?.let { query ->
            getSearchedNews(query)
        }
    }

    private fun getSearchedNews(query: String) {
        searchUseCase(query).onEach { result ->
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