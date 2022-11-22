package com.github.soup.review.infra.http.response

import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.member.infra.http.response.MemberResponse
import java.time.LocalDateTime

data class ReviewResponse(
    var id: String,

    var from: MemberResponse,

    var to: MemberResponse,

    var group: GroupResponse,

    var createdAt: LocalDateTime,

    var updatedAt: LocalDateTime,

    var score: Float,

    var content: String,
)