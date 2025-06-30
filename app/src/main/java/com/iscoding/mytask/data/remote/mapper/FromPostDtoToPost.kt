package com.iscoding.mytask.data.remote.mapper

import com.iscoding.mytask.data.remote.dto.Post
import com.iscoding.mytask.domain.model.TimedPost

fun Post.toTimedPost(): TimedPost {
    return TimedPost(
        body = this.body,
        id = this.id,
        title = this.title,
        userId = this.userId,
        timeFetched = System.currentTimeMillis()
    )
}