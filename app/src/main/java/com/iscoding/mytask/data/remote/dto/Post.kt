package com.iscoding.mytask.data.remote.dto

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)