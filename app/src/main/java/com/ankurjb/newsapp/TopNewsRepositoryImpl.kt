package com.ankurjb.newsapp

import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.TopNews

class TopNewsRepositoryImpl(
    private val newsClient: NewsClient
): TopNewsRepository {

    override suspend fun getTopNews(): Either<TopNews> = newsClient.getTopNews()
}
