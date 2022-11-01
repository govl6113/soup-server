package com.github.soup.post.comment.infra.persistence

import com.github.soup.post.comment.domain.Comment
import com.github.soup.post.domain.Post
import java.util.*

interface CommentRepository {
    fun getById(commentId: String): Optional<Comment>

    fun save(comment: Comment): Comment

    fun getByPost(post: Post): List<Comment>

    fun delete(comment: Comment)

    fun deleteAllInBatch(comments: List<Comment>)
}