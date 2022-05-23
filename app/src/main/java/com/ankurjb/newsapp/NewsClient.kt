package com.ankurjb.newsapp

import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.TopNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsClient(
    private val newsAPI: NewsAPI
) {
    suspend fun getTopNews() = withContext(Dispatchers.IO) {
        val topNews = try {
            val response = newsAPI.getTopNews()
            if (response.isSuccessful && response.body() != null) {
                Either.Success(response.body()!!)
            } else {
                Either.Error("API not responding")
            }
        } catch (e: java.lang.Exception) {
            Either.Error("API not responding")
        }
        return@withContext topNews
    }
}