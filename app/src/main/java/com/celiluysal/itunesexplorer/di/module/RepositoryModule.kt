package com.celiluysal.itunesexplorer.di.module

import com.celiluysal.itunesexplorer.data.repository.RepositoryImpl
import com.celiluysal.itunesexplorer.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: RepositoryImpl): Repository

}