package com.iscoding.mytask.presentation.screens.detailsScreen

import com.iscoding.mytask.data.remote.dto.Post

data class DetailsPostState(
    val post: Post? = null,
    val isLoading: Boolean = false,
    var notFound: Boolean = false,

    )
