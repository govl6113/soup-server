package com.github.soup.post.infra.persistence

import com.github.soup.group.domain.Group
import com.github.soup.post.domain.Post
import com.github.soup.post.domain.PostTypeEnum
import org.springframework.data.domain.Pageable
import java.util.*

interface PostRepository {
    fun save(post: Post): Post

    fun getListByType(group: Group, type: PostTypeEnum, pageable: Pageable): List<Post>

    fun getById(postId: String): Optional<Post>

    fun deleteById(postId: String)
}