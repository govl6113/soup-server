package com.github.soup.group.participant.domain

import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.member.domain.Member
import org.springframework.data.domain.Pageable

interface ParticipantRepository {

    fun save(participant: Participant): Participant

    fun getByMemberIdAndGroup(memberId: String, group: Group): Participant?

    fun getByGroupAndIsAccepted(group: Group, pageable: Pageable, isAccepted: Boolean?): List<Participant>

    fun getByMemberAndGroupAndIsAccepted(member: Member, group: Group, isAccepted: Boolean?): Participant?

    fun getJoinList(member: Member, status: GroupStatusEnum, pageable: Pageable): List<Group>

    fun getParticipantCount(group: Group): Int
}