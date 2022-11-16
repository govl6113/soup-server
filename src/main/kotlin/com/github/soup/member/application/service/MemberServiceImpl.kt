package com.github.soup.member.application.service

import com.github.soup.member.domain.Member
import com.github.soup.member.domain.MemberRepository
import com.github.soup.member.exception.NotFoundMemberException
import com.github.soup.member.infra.http.response.MemberResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    private val memberRepository: MemberRepository
) : MemberService {

    override fun getByMemberId(memberId: String): Member {
        return memberRepository.getById(memberId) ?: throw NotFoundMemberException()
    }

    override fun me(memberId: String): MemberResponse {
        return memberRepository.getById(memberId)?.toResponse() ?: throw NotFoundMemberException()
    }
}