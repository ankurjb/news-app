package com.ankurjb.latestnews.wiring

import com.ankurjb.latestnews.repo.LatestNewsRepository
import com.ankurjb.latestnews.repo.LatestNewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LatestNewsRepositoryModule {

    @Binds
    abstract fun bindLatestNewsRepository(
        latestNewsRepositoryImpl: LatestNewsRepositoryImpl
    ): LatestNewsRepository

}