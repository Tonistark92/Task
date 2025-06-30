package com.iscoding.mytask.di

import com.iscoding.mytask.data.repository.PostsRepositoryImp
import com.iscoding.mytask.domain.repository.PostsRepository
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
    abstract fun bindPostsRepository(
        postsRepositoryImp: PostsRepositoryImp
    ): PostsRepository



}