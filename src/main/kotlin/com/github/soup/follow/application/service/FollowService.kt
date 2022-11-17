package com.github.soup.follow.application.service

import com.github.soup.follow.domain.Follow
import com.github.soup.member.domain.Member

interface FollowService {
    fun save(follow: Follow): Follow

    fun getFromList(member: Member): List<Follow>

    fun getToList(member: Member): List<Follow>

    fun getByMemberAndTo(member: Member, followId: String): Follow

    fun delete(follow: Follow)
}