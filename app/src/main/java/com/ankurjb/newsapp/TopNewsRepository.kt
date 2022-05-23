package com.ankurjb.newsapp

interface TopNewsRepository {
    suspend fun getTopNews()
}
