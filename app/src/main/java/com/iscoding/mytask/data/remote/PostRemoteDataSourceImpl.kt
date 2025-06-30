package com.iscoding.mytask.data.remote

import com.iscoding.mytask.data.remote.dto.Post


class PostRemoteDataSourceImpl(
    private val api: PostService
) : PostRemoteDataSource {

    override suspend fun getAllPosts(): List<Post> = api.getAllPosts()

    override suspend fun getPost(postId: Int): Post = api.getPost(postId)
}