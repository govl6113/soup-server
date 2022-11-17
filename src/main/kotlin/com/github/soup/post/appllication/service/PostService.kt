package com.github.soup.post.appllication.service

import com.github.soup.group.domain.Group
import com.github.soup.post.domain.Post
import com.github.soup.post.domain.PostTypeEnum
import org.springframework.data.domain.Page

interface PostService {
    fun save(post: Post): Post

    fun getByGroupAndType(group: Group, type: PostTypeEnum, page: Int): Page<Post>

    fun getById(postId: String): Post

    fun deleteById(postId: String)
}