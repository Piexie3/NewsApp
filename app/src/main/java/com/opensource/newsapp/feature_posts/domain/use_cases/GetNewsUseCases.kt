package com.opensource.newsapp.feature_posts.domain.use_cases

import com.manubett.news.core.util.Resource
import com.opensource.newsapp.feature_posts.data.dto.toNewsItem
import com.opensource.newsapp.feature_posts.domain.model.NewsItem
import com.opensource.newsapp.feature_posts.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCases @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<NewsItem>>> = flow {
        try {
            emit(Resource.Loading())
            val article = repository.getNews().toNewsItem()
            emit(Resource.Success(article))

        }catch(e: HttpException){
            emit(Resource.Error(e.localizedMessage?:"An  unexpected Error occurred!"))

        }catch(e: IOException){
            emit(Resource.Error("Couldn't reach server. Please check your Internet Connections "))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage?: "Unknown error"))
        }
    }
}
