package com.iscoding.mytask.domain.model

data class TimedPost(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val timeFetched: Long
)