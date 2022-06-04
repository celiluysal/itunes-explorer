package com.celiluysal.itunesexplorer.data.remote

import com.celiluysal.itunesexplorer.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val REQUEST_CONNECT_TIMEOUT: Long = 10
private const val REQUEST_WRITE_TIMEOUT: Long = 10
private const val REQUEST_READ_TIMEOUT: Long = 10

private const val HEADER_CONTENT_TYPE_KEY = "Content-Type"
private const val HEADER_CONTENT_TYPE_VALUE = "application/json"

@Singleton
class NetworkManager @Inject constructor() {
    private val headerInterceptor
        get() = Interceptor { chain ->
            val chainRequest = chain.request()
            val builder = chainRequest.newBuilder()
                .method(chainRequest.method, chainRequest.body)
                .header(HEADER_CONTENT_TYPE_KEY, HEADER_CONTENT_TYPE_VALUE)
                .build()
            chain.proceed(builder)
        }

    private val loggingInterceptor: HttpLoggingInterceptor
        get() = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        else
            HttpLoggingInterceptor()


    private val okHttpBuilder: OkHttpClient.Builder
        get() = OkHttpClient.Builder().addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(REQUEST_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_READ_TIMEOUT, TimeUnit.SECONDS)


    private val retrofitBuilder: Retrofit.Builder
        get() {
            okHttpBuilder.apply {
                return Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL).client(build())
                    .addConverterFactory(MoshiConverterFactory.create())
            }
        }

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofitBuilder.build().create(serviceClass)
    }

}