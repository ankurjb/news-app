package com.ankurjb.topnews.wiring

import com.ankurjb.topnews.repo.TopNewsRepository
import com.ankurjb.topnews.repo.TopNewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TopNewsRepositoryModule {
    @Binds
    abstract fun bindTopNewsRepository(
        topNewsRepositoryImpl: TopNewsRepositoryImpl
    ): TopNewsRepository

}