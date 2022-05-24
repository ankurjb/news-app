package com.ankurjb.latestnews.repo

import com.ankurjb.core.network.NewsClient
import com.ankurjb.core.model.Either
import com.ankurjb.core.model.News
import javax.inject.Inject

class LatestNewsRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient
) : LatestNewsRepository {

    override suspend fun getLatestNews(
        pageNumber: Int
    ): Either<News> = newsClient.getLatestNews(
        pageNumber
    )
}
