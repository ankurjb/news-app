package com.ankurjb.newsapp

import com.ankurjb.newsapp.model.Either
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

    suspend fun getLatestNews() = withContext(Dispatchers.IO) {
        val latestNews = try {
            val response = newsAPI.getLatestNews()
            if (response.isSuccessful && response.body() != null) {
                Either.Success(response.body()!!)
            } else {
                Either.Error("API not responding")
            }
        } catch (e: java.lang.Exception) {
            Either.Error("API not responding")
        }
        return@withContext latestNews
    }
}