package com.celiluysal.itunesexplorer.di.module

import com.celiluysal.itunesexplorer.BuildConfig
import com.celiluysal.itunesexplorer.data.remote.*
import com.celiluysal.itunesexplorer.data.remote.service.ItunesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val REQUEST_CONNECT_TIMEOUT: Long = 10
        private const val REQUEST_WRITE_TIMEOUT: Long = 10
        private const val REQUEST_READ_TIMEOUT: Long = 10

        private const val HEADER_CONTENT_TYPE_KEY = "Content-Type"
        private const val HEADER_CONTENT_TYPE_VALUE = "application/json"
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val chainRequest = chain.request()
            val builder = chainRequest.newBuilder()
                .method(chainRequest.method, chainRequest.body)
                .header(HEADER_CONTENT_TYPE_KEY, HEADER_CONTENT_TYPE_VALUE)
                .build()
            chain.proceed(builder)
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        else
            HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        headerInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(REQUEST_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttp: OkHttpClient): Retrofit {
            okHttp.apply {
                return Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL).client(this)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            }
        }

    @Provides
    @Singleton
    fun provideItunesService(retrofit: Retrofit): ItunesService {
        return retrofit.create(ItunesService::class.java)
    }

}