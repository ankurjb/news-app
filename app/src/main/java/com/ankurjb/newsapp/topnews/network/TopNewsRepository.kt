package com.ankurjb.newsapp.topnews.network

import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News

interface TopNewsRepository {
    suspend fun getTopNews(): Either<News>
}
