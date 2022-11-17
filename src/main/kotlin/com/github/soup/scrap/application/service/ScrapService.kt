package com.github.soup.scrap.application.service

import com.github.soup.group.domain.Group
import com.github.soup.member.domain.Member
import com.github.soup.scrap.domain.Scrap

interface ScrapService {

    fun save(scrap: Scrap): Scrap

    fun getById(scrapId: String): Scrap

    fun getByMember(member: Member): List<Scrap>

    fun getByMemberAndGroup(member: Member, group: Group): Scrap?
    
    fun delete(scrap: Scrap)
}