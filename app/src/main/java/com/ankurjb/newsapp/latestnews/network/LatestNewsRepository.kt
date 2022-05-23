package com.ankurjb.newsapp.latestnews.network

import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News

interface LatestNewsRepository {
    suspend fun getLatestNews(): Either<News>
}
