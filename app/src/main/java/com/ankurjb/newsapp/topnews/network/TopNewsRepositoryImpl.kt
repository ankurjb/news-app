package com.ankurjb.newsapp.topnews.network

import com.ankurjb.newsapp.NewsClient
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News
import javax.inject.Inject

class TopNewsRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient
) : TopNewsRepository {

    override suspend fun getTopNews(): Either<News> = newsClient.getTopNews()
}
