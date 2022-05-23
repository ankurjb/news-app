package com.ankurjb.topnews.repo

import com.ankurjb.core.model.Either
import com.ankurjb.core.model.News

interface TopNewsRepository {
    suspend fun getTopNews(): Either<News>
}
