package com.github.soup.member.infra.persistence

import com.github.soup.member.domain.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, String> {

    fun findByNameContaining(name: String, pageable: Pageable): Page<Member>
}