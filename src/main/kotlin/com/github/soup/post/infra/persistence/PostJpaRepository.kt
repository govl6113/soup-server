package com.github.soup.post.infra.persistence

import com.github.soup.group.domain.Group
import com.github.soup.post.domain.Post
import com.github.soup.post.domain.PostTypeEnum
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, String> {
    fun findByGroupAndType(group: Group, type: PostTypeEnum, pageable: Pageable): List<Post>
}