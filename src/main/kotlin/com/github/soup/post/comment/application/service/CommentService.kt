package com.github.soup.post.comment.application.service

import com.github.soup.post.comment.domain.Comment
import com.github.soup.post.domain.Post

interface CommentService {

    fun getById(commentId: String): Comment

    fun save(comment: Comment): Comment

    fun getByPost(post: Post): List<Comment>

    fun deleteById(comment: Comment)
    
    fun deleteByPost(post: Post)
}