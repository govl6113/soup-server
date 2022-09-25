package com.github.soup.member.infra.persistence

import com.github.soup.member.domain.Member
import com.github.soup.member.domain.MemberRepository
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
	private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {
	override fun getById(memberId: String): Member? {
		return memberJpaRepository.findById(memberId).orElse(null)
	}

	override fun save(member: Member): Member {
		return memberJpaRepository.save(member)
	}
}