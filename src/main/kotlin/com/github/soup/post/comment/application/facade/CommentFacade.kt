package com.github.soup.post.comment.application.facade

import com.github.soup.post.comment.infra.http.request.CreateCommentRequest
import com.github.soup.post.comment.infra.http.response.CommentResponse

interface CommentFacade {

    fun create(memberId: String, request: CreateCommentRequest): CommentResponse

    fun delete(memberId: String, commentId: String): Boolean
}