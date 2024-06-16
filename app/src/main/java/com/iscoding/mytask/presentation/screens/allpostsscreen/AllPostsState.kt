package com.iscoding.mytask.presentation.screens.allpostsscreen

import com.iscoding.mytask.data.remote.dto.Post
import com.iscoding.mytask.util.UiText

data class AllPostsState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    var notFound: Boolean = false,
    var errorMessage: UiText? = null,

)
