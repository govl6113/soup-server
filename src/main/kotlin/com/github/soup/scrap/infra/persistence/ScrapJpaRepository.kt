package com.github.soup.scrap.infra.persistence

import com.github.soup.group.domain.Group
import com.github.soup.member.domain.Member
import com.github.soup.scrap.domain.Scrap
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ScrapJpaRepository : JpaRepository<Scrap, String> {

    fun findByMember(member: Member): List<Scrap>

    fun findByMemberAndGroup(member: Member, group: Group): Optional<Scrap>
}