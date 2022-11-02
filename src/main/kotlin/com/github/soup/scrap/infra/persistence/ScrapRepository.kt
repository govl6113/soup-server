package com.github.soup.scrap.infra.persistence

import com.github.soup.group.domain.Group
import com.github.soup.member.domain.Member
import com.github.soup.scrap.domain.Scrap
import java.util.*

interface ScrapRepository {
    fun save(scrap: Scrap): Scrap

    fun getById(scrapId: String): Optional<Scrap>

    fun getByMember(member: Member): List<Scrap>

    fun getByMemberAndGroup(member: Member, group: Group): Optional<Scrap>

    fun delete(scrap: Scrap)
}