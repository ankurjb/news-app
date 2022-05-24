package com.ankurjb.topnews.repo

import com.ankurjb.core.model.Either
import com.ankurjb.core.model.News
import com.ankurjb.core.network.NewsClient
import javax.inject.Inject

class TopNewsRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient
) : TopNewsRepository {

    override suspend fun getTopNews(
        pageNumber: Int
    ): Either<News> = newsClient.getTopNews(
        pageNumber
    )
}
