package com.iscoding.mytask.domain.usecases

import com.iscoding.mytask.domain.repository.PostsRepository
import javax.inject.Inject

class GetPost @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(id: Int) = repository.getPost(id = id)
}