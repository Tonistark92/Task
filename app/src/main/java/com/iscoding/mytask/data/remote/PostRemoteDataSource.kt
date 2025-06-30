package com.iscoding.mytask.data.remote

import com.iscoding.mytask.data.remote.dto.Post

interface PostRemoteDataSource {
    suspend fun getAllPosts(): List<Post>
    suspend fun getPost(postId: Int): Post
}