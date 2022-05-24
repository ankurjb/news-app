package com.ankurjb.latestnews.repo

import com.ankurjb.core.model.Either
import com.ankurjb.core.model.News

interface LatestNewsRepository {
    suspend fun getLatestNews(pageNumber: Int): Either<News>
}
