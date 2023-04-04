package com.opensource.newsapp.feature_posts.domain.use_cases

import com.manubett.news.core.util.Resource
import com.opensource.newsapp.feature_posts.data.dto.toNewsDetails
import com.opensource.newsapp.feature_posts.domain.model.NewsDetails
import com.opensource.newsapp.feature_posts.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsDetailsUseCases @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<NewsDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val article = repository.getNewsDetails().toNewsDetails()
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