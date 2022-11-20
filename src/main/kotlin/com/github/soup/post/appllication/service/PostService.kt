package com.github.soup.post.appllication.service

import com.github.soup.group.domain.Group
import com.github.soup.post.domain.Post
import com.github.soup.post.domain.PostTypeEnum

interface PostService {
    fun save(post: Post): Post

    fun getByGroupAndType(group: Group, type: PostTypeEnum, page: Int): List<Post>

    fun getById(postId: String): Post

    fun deleteById(postId: String)
}