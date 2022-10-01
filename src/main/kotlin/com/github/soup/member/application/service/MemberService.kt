package com.github.soup.member.application.service

import com.github.soup.member.domain.Member

interface MemberService {
    fun getByMemberId(memberId: String): Member
}