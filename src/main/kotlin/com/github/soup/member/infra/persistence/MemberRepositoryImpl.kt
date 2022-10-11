package com.github.soup.member.infra.persistence

import com.github.soup.member.domain.Member
import com.github.soup.member.domain.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberRepository: MemberJpaRepository
) : MemberRepository {
    override fun getById(memberId: String): Member? {
        return memberRepository.findById(memberId).orElse(null)
    }

    override fun save(member: Member): Member {
        return memberRepository.save(member)
    }

    override fun searchMember(name: String, pageable: Pageable): List<Member> {
        return memberRepository.findByNicknameContaining(name, pageable)
    }
}