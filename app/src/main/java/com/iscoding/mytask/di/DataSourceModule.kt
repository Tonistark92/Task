package com.iscoding.mytask.di

import com.iscoding.mytask.data.remote.PostRemoteDataSource
import com.iscoding.mytask.data.remote.PostRemoteDataSourceImpl
import com.iscoding.mytask.data.remote.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun providePostRemoteDataSource(
        api: PostService
    ): PostRemoteDataSource = PostRemoteDataSourceImpl(api)
}