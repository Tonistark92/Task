package com.iscoding.mytask.presentation.screens.allpostsscreen

import com.iscoding.mytask.domain.model.TimedPost
import com.iscoding.mytask.presentation.util.UiText

data class AllPostsState(
    val posts: List<TimedPost> = emptyList(),
    val isLoading: Boolean = false,
    var notFound: Boolean = false,
    var errorMessage: UiText? = null,

    )
