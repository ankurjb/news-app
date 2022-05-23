package com.ankurjb.core.network

import com.ankurjb.core.BuildConfig
import com.ankurjb.core.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") cityName: String = "in",
        @Query("apiKey") secretKey: String = BuildConfig.API_KEY
    ): Response<News>

    @GET("everything")
    suspend fun getLatestNews(
        @Query("q") query: String = "india",
        @Query("apiKey") secretKey: String = BuildConfig.API_KEY
    ): Response<News>
}
