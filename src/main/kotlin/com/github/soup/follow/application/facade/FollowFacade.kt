package com.github.soup.follow.application.facade

import com.github.soup.follow.infra.http.response.FollowResponse
import com.github.soup.member.infra.http.response.MemberResponse

interface FollowFacade {
    fun create(fromId: String, targetId: String): FollowResponse

    fun getFollowingList(memberId: String): List<MemberResponse>

    fun getFollowerList(memberId: String): List<MemberResponse>

    fun delete(memberId: String, followId: String): Boolean
}