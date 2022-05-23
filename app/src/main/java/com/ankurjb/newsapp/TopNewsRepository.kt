package com.ankurjb.newsapp

import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.TopNews

interface TopNewsRepository {
    suspend fun getTopNews(): Either<TopNews>
}
