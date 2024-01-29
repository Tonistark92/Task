package com.iscoding.mytask.presentation.screens.allpostsscreen

import com.iscoding.mytask.data.remote.dto.Post

data class AllPostsState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = true,
    var notFound: Boolean = false,

)
