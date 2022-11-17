package com.github.soup.scrap.infra.http.response

import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.member.infra.http.response.MemberResponse
import java.time.LocalDateTime

data class ScrapResponse(

    val id: String,
    val member: MemberResponse,
    val group: GroupResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
