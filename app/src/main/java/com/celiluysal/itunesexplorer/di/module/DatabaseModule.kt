package com.celiluysal.itunesexplorer.di.module

import android.app.Application
import com.celiluysal.itunesexplorer.data.local.ItunesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = ItunesDatabase.getDatabase(application)

}