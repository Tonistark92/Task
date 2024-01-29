package com.iscoding.mytask.domain.repository

import com.iscoding.mytask.data.remote.dto.Post
import com.iscoding.mytask.util.Resource
import kotlinx.coroutines.flow.Flow

interface  PostsRepository {
    fun getAllPosts():Flow<Resource<List<Post>>>
    fun getPost(id :Int):Flow<Resource<Post>>
}