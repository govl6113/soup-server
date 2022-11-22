package com.github.soup.group.participant.application.service

import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.participant.domain.Participant
import com.github.soup.group.participant.infra.http.response.ParticipantResponse
import com.github.soup.member.domain.Member
import com.github.soup.member.infra.http.response.MemberResponse

interface ParticipantService {
	fun save(group: Group, member: Member, isAccepted: Boolean, message: String): Boolean

	fun getParticipantList(group: Group): List<ParticipantResponse>

	fun getByMemberIdAndGroup(memberId: String, group: Group): Participant?

	fun checkRegister(member: Member, group: Group): Boolean

	fun checkParticipant(member: Member, group: Group): Boolean

	fun joinGroupList(member: Member, status: GroupStatusEnum, page: Int): List<Group>

	fun members(group: Group): List<MemberResponse>

	fun getParticipantCount(group: Group): Int
}