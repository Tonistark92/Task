package com.iscoding.mytask.data.repository

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
    override fun getAllPosts(): Flow<Resource<List<Post>>> = flow {
//        Log.d("HI","In REPO")

        emit(Resource.Loading(isLoading = true))
        val list = api.getAllPosts()
//        Log.d("HI",list.toString())
        emit(Resource.Success(data = list))
        emit(Resource.Loading(isLoading = false))
    }

    override fun getPost(id: Int): Flow<Resource<Post>> = flow {
//        Log.d("HI","In REPO")

        emit(Resource.Loading(isLoading = true))
        val post = api.getPost(postId = id  )
//        Log.d("HI",post.toString())
        emit(Resource.Success(data = post))
        emit(Resource.Loading(isLoading = false))
    }
}