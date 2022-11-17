package com.github.soup.follow.infra.http.response

import com.github.soup.member.infra.http.response.MemberResponse
import java.time.LocalDateTime

data class FollowResponse(
    val id: String,
    val from: MemberResponse,
    val to: MemberResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)