package com.opensource.newsapp.di

import com.opensource.newsapp.feature_posts.domain.use_cases.GetNewsUseCases
import com.manubett.news.core.util.Constants.BASE_URL
import com.manubett.news.feature_posts.data.remote.NewsApi
import com.manubett.news.feature_posts.data.repository.NewsRepositoryImpl
import com.opensource.newsapp.feature_posts.domain.repository.NewsRepository
import com.opensource.newsapp.feature_posts.domain.use_cases.GetNewsDetailsUseCases
import com.opensource.newsapp.feature_posts.domain.use_cases.SearchUseCase
import com.opensource.newsapp.feature_posts.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesGetUseCases(repository: NewsRepository): UseCases {
        return UseCases(
            getNewsDetailsUseCases = GetNewsDetailsUseCases(repository),
            getNewsUseCases = GetNewsUseCases(repository),
            searchUseCase = SearchUseCase(repository)
        )
    }
}