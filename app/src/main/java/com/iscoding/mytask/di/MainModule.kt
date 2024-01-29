package com.iscoding.mytask.di

import com.google.gson.Gson
import com.iscoding.mytask.data.remote.PostRemoteDataSource
import com.iscoding.mytask.domain.Constatns
import com.iscoding.mytask.domain.repository.PostsRepository
import com.iscoding.mytask.domain.usecases.GetAllPosts
import com.iscoding.mytask.domain.usecases.GetPost
import com.iscoding.mytask.domain.usecases.PostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideRetrofit(): PostRemoteDataSource {

        return Retrofit.Builder()
            .baseUrl(Constatns.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun providePostUseCase (postRepository: PostsRepository ) = PostsUseCase(
        getAllPosts = GetAllPosts(postRepository),
        getPost = GetPost(postRepository)
    )

}