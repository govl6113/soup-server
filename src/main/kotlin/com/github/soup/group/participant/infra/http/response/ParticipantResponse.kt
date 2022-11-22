package com.github.soup.group.participant.infra.http.response

import com.github.soup.member.domain.Member
import com.github.soup.member.infra.http.response.MemberResponse
import java.time.LocalDateTime

data class ParticipantResponse (
    var id: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var member: MemberResponse,
    var message: String
){
    constructor(
        id: String,
        createdAt: LocalDateTime,
        updatedAt: LocalDateTime,
        member: Member,
        message: String
    ):
            this(id, createdAt, updatedAt,member.toResponse(), message)
}