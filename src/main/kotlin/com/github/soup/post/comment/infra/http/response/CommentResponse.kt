package com.github.soup.post.comment.infra.http.response

import com.github.soup.member.infra.http.response.MemberResponse
import java.time.LocalDateTime

data class CommentResponse(
    val id: String,
    val writer: MemberResponse,
    val content: String,
    val child: List<CommentResponse>?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
