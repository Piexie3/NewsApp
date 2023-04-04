package com.manubett.news.feature_posts.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.manubett.news.core.util.usersCurrentDate
import com.opensource.newsapp.feature_posts.data.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @RequiresApi(Build.VERSION_CODES.O)
    @GET("/search")
    suspend fun getAllNews(
        @Query("api-key") apiKey: String = "ca485296-56cf-461c-a792-816e10a5ccb0",
        @Query("page-size") pageSize: String = "150",
        @Query("from-date") fromDate: String? =  "2022-01-25",
        @Query("to-date") toDate: String? = usersCurrentDate,
        @Query("order-by") order: String = "newest",
        @Query("use-date") useDate: String = "published",
        @Query("show-tags") tags: String = "contributor",
        @Query("show-fields") fields: String = "all",
        @Query("format") format: String = "json",
    ): NewsDto

    @RequiresApi(Build.VERSION_CODES.O)
    @GET("/search")
    suspend fun getDetailedNews(
        @Query("api-key") apiKey: String = "ca485296-56cf-461c-a792-816e10a5ccb0",
        @Query("page-size") pageSize: String = "150",
        @Query("from-date") fromDate: String? =  "2022-01-25",
        @Query("to-date") toDate: String? = usersCurrentDate,
        @Query("order-by") order: String = "newest",
        @Query("use-date") useDate: String = "published",
        @Query("show-tags") tags: String = "contributor",
        @Query("show-fields") fields: String = "all",
        @Query("format") format: String = "json",
    ): NewsDto

    @RequiresApi(Build.VERSION_CODES.O)
    @GET("/search")
    suspend fun searchNews(
        @Query("api-key") apiKey: String = "ca485296-56cf-461c-a792-816e10a5ccb0",
        @Query("page-size") pageSize: String = "150",
        @Query("from-date") fromDate: String? =  "2022-01-25",
        @Query("to-date") toDate: String? = usersCurrentDate,
        @Query("order-by") order: String = "newest",
        @Query("use-date") useDate: String = "published",
        @Query("show-tags") tags: String = "contributor",
        @Query("show-fields") fields: String = "all",
        @Query("format") format: String = "json",
        @Query("q") query: String,
    ): NewsDto
}