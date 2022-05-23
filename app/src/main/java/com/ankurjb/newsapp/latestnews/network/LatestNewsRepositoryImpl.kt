package com.ankurjb.newsapp.latestnews.network

import com.ankurjb.newsapp.NewsClient
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News
import javax.inject.Inject

class LatestNewsRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient
) : LatestNewsRepository {

    override suspend fun getLatestNews(): Either<News> = newsClient.getLatestNews()
}
