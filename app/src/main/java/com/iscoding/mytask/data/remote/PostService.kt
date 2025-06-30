package com.iscoding.mytask.data.remote

import com.iscoding.mytask.data.remote.dto.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    suspend  fun getAllPosts():List<Post>
    @GET("posts/{postId}")
    suspend  fun getPost( @Path("postId")postId :Int): Post
}