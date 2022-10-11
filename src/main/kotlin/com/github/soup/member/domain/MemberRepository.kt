package com.github.soup.member.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemberRepository {
    fun getById(memberId: String): Member?
    fun save(member: Member): Member
    fun searchMember(name: String, pageable: Pageable): Page<Member>
}