package com.github.soup.follow.domain

import com.github.soup.common.domain.Core
import com.github.soup.follow.infra.http.response.FollowResponse
import com.github.soup.member.domain.Member
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Follow(

    @ManyToOne
    @JoinColumn(name = "from_id")
    var from: Member,

    @ManyToOne
    @JoinColumn(name = "to_id")
    var to: Member,
) : Core() {

    fun toResponse(): FollowResponse {
        return FollowResponse(
            id = id!!,
            from = from.toResponse(),
            to = to.toResponse(),
            createdAt = createdAt!!,
            updatedAt = updatedAt!!
        )
    }
}