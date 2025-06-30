package com.iscoding.mytask.di

import com.iscoding.mytask.data.remote.PostService
import com.iscoding.mytask.data.remote.RetryInterceptor
import com.iscoding.mytask.data.remote.util.Constatns
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =  OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .connectTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(RetryInterceptor(maxRetries = 3, retryIntervalSeconds = 2))
        .build()

    @Provides @Singleton
    fun provideRetrofit(client: OkHttpClient): PostService =
        Retrofit.Builder()
            .baseUrl(Constatns.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PostService::class.java)
}