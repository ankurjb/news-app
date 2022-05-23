package com.ankurjb.newsapp.wiring

import com.ankurjb.latestnews.LatestNewsExecutor
import com.ankurjb.topnews.TopNewsExecutor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NewsRepositoryModule {
    @Binds
    abstract fun bindTopNewsExecutor(
        topNewsExecutorImpl: TopNewsExecutorImpl
    ): TopNewsExecutor

    @Binds
    abstract fun bind(
        latestNewsExecutorImpl: LatestNewsExecutorImpl
    ): LatestNewsExecutor

}