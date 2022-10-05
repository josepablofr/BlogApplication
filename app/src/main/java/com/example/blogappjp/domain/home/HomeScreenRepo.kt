package com.example.blogappjp.domain.home

import com.example.blogappjp.core.Result
import com.example.blogappjp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Result<List<Post>>
    suspend fun registerLikeButtonState(postId: String, liked: Boolean)
}