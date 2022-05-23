package com.ankurjb.newsapp.topnews.network

import com.ankurjb.newsapp.NewsClient
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News

class TopNewsRepositoryImpl(
    private val newsClient: NewsClient
) : TopNewsRepository {

    override suspend fun getTopNews(): Either<News> = newsClient.getTopNews()
}
