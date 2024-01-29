package com.iscoding.mytask.domain.usecases

import com.iscoding.mytask.domain.repository.PostsRepository
import javax.inject.Inject

class GetAllPosts @Inject constructor(
private val repository: PostsRepository
) {
    operator fun invoke() = repository.getAllPosts()
}