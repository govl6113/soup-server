package com.github.soup.follow.infra.persistence

import com.github.soup.follow.domain.Follow
import com.github.soup.member.domain.Member

interface FollowRepository {

    fun save(follow: Follow): Follow

    fun getByFrom(from: Member): List<Follow>

    fun getByTo(to: Member): List<Follow>

    fun getByMemberAndTo(member: Member, followId: String): Follow?

    fun delete(follow: Follow)
}