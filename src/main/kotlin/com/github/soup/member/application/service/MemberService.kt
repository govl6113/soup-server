package com.github.soup.member.application.service

import com.github.soup.member.domain.Member
import com.github.soup.member.infra.http.response.MemberResponse

interface MemberService {
    fun getByMemberId(memberId: String): Member
    fun me(memberId: String): MemberResponse
}