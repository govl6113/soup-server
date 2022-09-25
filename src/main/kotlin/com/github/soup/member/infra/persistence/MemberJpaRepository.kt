package com.github.soup.member.infra.persistence

import com.github.soup.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, String>