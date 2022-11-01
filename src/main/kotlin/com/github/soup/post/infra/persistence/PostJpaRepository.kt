package com.github.soup.post.infra.persistence

import com.github.soup.post.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, String>