package com.iscoding.mytask.domain.usecases

data class PostsUseCase(
    var getAllPosts: GetAllPosts,
    var getPost: GetPost
)