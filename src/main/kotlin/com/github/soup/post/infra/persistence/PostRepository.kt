package com.github.soup.post.infra.persistence

import com.github.soup.post.domain.Post
import java.util.*

interface PostRepository {
    fun save(post: Post): Post

    fun getById(postId: String): Optional<Post>

    fun deleteById(postId: String)
}