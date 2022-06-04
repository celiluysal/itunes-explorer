package com.celiluysal.itunesexplorer.di.module

import com.celiluysal.itunesexplorer.data.repository.Repository
import com.celiluysal.itunesexplorer.data.repository.RepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: Repository): RepositorySource

}