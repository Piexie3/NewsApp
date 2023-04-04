package com.opensource.newsapp.feature_posts.domain.repository

import com.opensource.newsapp.feature_posts.data.dto.NewsDto

interface NewsRepository  {
    suspend fun getNews(): NewsDto
    suspend fun getNewsDetails(): NewsDto

    suspend fun searchNews(query:String): NewsDto
}