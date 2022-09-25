package com.github.soup.member.domain

interface MemberRepository {
	fun getById(memberId: String): Member?
	fun save(member: Member): Member
}