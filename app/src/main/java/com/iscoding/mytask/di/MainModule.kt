package com.iscoding.mytask.di

import com.google.gson.Gson
import com.iscoding.mytask.data.remote.PostRemoteDataSource
import com.iscoding.mytask.data.remote.PostRemoteDataSourceImpl
import com.iscoding.mytask.data.remote.PostService
import com.iscoding.mytask.data.remote.RetryInterceptor
import com.iscoding.mytask.data.remote.util.Constatns
import com.iscoding.mytask.domain.repository.PostsRepository
import com.iscoding.mytask.domain.usecases.GetAllPosts
import com.iscoding.mytask.domain.usecases.GetPost
import com.iscoding.mytask.domain.usecases.PostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(RetryInterceptor(maxRetries = 3, retryIntervalSeconds = 2))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): PostService {

        return Retrofit.Builder()
            .baseUrl(Constatns.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
            .build()
            .create()
    }


    @Provides
    fun providePostRemoteDataSource(
        api: PostService
    ): PostRemoteDataSource = PostRemoteDataSourceImpl(api)

    @Singleton
    @Provides
    fun providePostUseCase (postRepository: PostsRepository ) = PostsUseCase(
        getAllPosts = GetAllPosts(postRepository),
        getPost = GetPost(postRepository)
    )

}