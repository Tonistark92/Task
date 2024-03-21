package com.iscoding.mytask.data.repository

import android.util.Log
import com.iscoding.mytask.data.remote.PostRemoteDataSource
import com.iscoding.mytask.data.remote.dto.Post
import com.iscoding.mytask.domain.repository.PostsRepository
import com.iscoding.mytask.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    private val api: PostRemoteDataSource,
) :PostsRepository {
    private var count =0
    override fun getAllPosts(): Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading(isLoading = true))
        Log.d("ISLAM","COUNT IN REPO IS $count")
        val list = api.getAllPosts()
        emit(Resource.Success(data = list))
        emit(Resource.Loading(isLoading = false))
        ++count

    }

    override fun getPost(id: Int): Flow<Resource<Post>> = flow {

        emit(Resource.Loading(isLoading = true))
        val post = api.getPost(postId = id  )
        emit(Resource.Success(data = post))
        emit(Resource.Loading(isLoading = false))
    }
}