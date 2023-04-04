package com.opensource.newsapp.feature_posts.domain.use_cases

data class UseCases(
    val getNewsDetailsUseCases: GetNewsDetailsUseCases,
    val searchUseCase: SearchUseCase,
    val getNewsUseCases: GetNewsUseCases
)
