package com.github.soup.member.application.service

import com.github.soup.member.domain.Member
import com.github.soup.member.domain.MemberRepository
import com.github.soup.member.exception.NotFoundMemberException
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
) : MemberService {

    override fun getByMemberId(memberId: String): Member {
        return memberRepository.getById(memberId) ?: throw NotFoundMemberException()
    }
}