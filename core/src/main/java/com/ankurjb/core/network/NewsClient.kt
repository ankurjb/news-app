package com.ankurjb.core.network

import com.ankurjb.core.model.Either
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsClient @Inject constructor(
    private val newsAPI: NewsAPI
) {
    suspend fun getTopNews(
        pageNumber: Int
    ) = withContext(Dispatchers.IO) {
        val topNews = try {
            val response = newsAPI.getTopNews(
                page = pageNumber
            )
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

    suspend fun getLatestNews(
        pageNumber: Int
    ) = withContext(Dispatchers.IO) {
        val latestNews = try {
            val response = newsAPI.getLatestNews(
                page = pageNumber
            )
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
