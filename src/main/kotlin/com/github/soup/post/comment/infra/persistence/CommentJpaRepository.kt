package com.github.soup.post.comment.infra.persistence

import com.github.soup.post.comment.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository : JpaRepository<Comment, String>