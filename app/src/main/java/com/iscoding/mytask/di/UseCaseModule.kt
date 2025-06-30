package com.iscoding.mytask.di

import com.iscoding.mytask.domain.repository.PostsRepository
import com.iscoding.mytask.domain.usecases.GetAllPosts
import com.iscoding.mytask.domain.usecases.GetPost
import com.iscoding.mytask.domain.usecases.PostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providePostsUseCase(
        repository: PostsRepository
    ) = PostsUseCase(
        getAllPosts = GetAllPosts(repository),
        getPost = GetPost(repository)
    )
}