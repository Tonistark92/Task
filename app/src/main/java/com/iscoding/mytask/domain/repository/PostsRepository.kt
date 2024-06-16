package com.iscoding.mytask.domain.repository

import com.iscoding.mytask.data.remote.dto.Post
import com.iscoding.mytask.domain.error.DataError
import com.iscoding.mytask.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun  getAllPosts(): Flow<com.iscoding.mytask.domain.error.Result<List<Post>, DataError.Network>>
    suspend fun getPost(id: Int): Flow<com.iscoding.mytask.domain.error.Result<Post, DataError.Network>>
}