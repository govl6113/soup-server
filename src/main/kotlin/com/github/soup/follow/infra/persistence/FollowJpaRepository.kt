package com.github.soup.follow.infra.persistence

import com.github.soup.follow.domain.Follow
import com.github.soup.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface FollowJpaRepository : JpaRepository<Follow, String> {
    fun findByFrom(from: Member): List<Follow>

    fun findByTo(to: Member): List<Follow>

    fun getByFromAndToId(from: Member, toId: String): Follow?
}