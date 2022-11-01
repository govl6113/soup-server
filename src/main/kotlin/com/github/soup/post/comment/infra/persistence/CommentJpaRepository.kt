package com.github.soup.post.comment.infra.persistence

import com.github.soup.post.comment.domain.Comment
import com.github.soup.post.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository : JpaRepository<Comment, String> {

    fun findByPost(post: Post): List<Comment>

}