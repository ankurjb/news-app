package com.ankurjb.newsapp

class TopNewsRepositoryImpl(
    private val newsClient: NewsClient
): TopNewsRepository {

    override suspend fun getTopNews() = newsClient.getTopNews()
}
