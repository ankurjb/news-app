package com.ankurjb.newsapp

import com.ankurjb.newsapp.model.TopNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") cityName: String = "in",
        @Query("apiKey") secretKey: String = "9c7351df598940f7a6f0d063581f2f83"
    ): Response<TopNews>
}
