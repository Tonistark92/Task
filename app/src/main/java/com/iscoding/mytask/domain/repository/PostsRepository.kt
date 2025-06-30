package com.iscoding.mytask.domain.repository

import com.iscoding.mytask.data.remote.dto.Post
import com.iscoding.mytask.domain.error_models.DataError
import com.iscoding.mytask.domain.error_models.Result
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun  getAllPosts(): Flow<Result<List<Post>, DataError.Network>>
    suspend fun getPost(id: Int): Flow<Result<Post, DataError.Network>>
}