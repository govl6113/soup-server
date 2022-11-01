package com.github.soup.post.appllication.service

import com.github.soup.post.domain.Post

interface PostService {
    fun save(post: Post): Post

    fun getById(postId: String): Post

    fun deleteById(postId: String)
}