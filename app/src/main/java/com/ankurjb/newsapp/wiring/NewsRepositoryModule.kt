package com.ankurjb.newsapp.wiring

import com.ankurjb.newsapp.latestnews.network.LatestNewsRepository
import com.ankurjb.newsapp.latestnews.network.LatestNewsRepositoryImpl
import com.ankurjb.newsapp.topnews.network.TopNewsRepository
import com.ankurjb.newsapp.topnews.network.TopNewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsRepositoryModule {
    @Binds
    abstract fun bindTopNewsRepository(
        topNewsRepositoryImpl: TopNewsRepositoryImpl
    ): TopNewsRepository

    @Binds
    abstract fun bindLatestNewsRepository(
        latestNewsRepositoryImpl: LatestNewsRepositoryImpl
    ): LatestNewsRepository

}
