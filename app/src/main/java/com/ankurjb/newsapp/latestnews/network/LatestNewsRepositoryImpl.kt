package com.ankurjb.newsapp.latestnews.network

import com.ankurjb.newsapp.NewsClient
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News

class LatestNewsRepositoryImpl(
    private val newsClient: NewsClient
) : LatestNewsRepository {

    override suspend fun getLatestNews(): Either<News> = newsClient.getLatestNews()
}