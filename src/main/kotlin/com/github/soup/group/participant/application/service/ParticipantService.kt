package com.github.soup.group.participant.application.service

import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.member.domain.Member

interface ParticipantService {
    fun save(group: Group, member: Member, isAccepted: Boolean, message: String): Boolean

    fun checkParticipant(member: Member, group: Group)

    fun joinGroupList(member: Member, status: GroupStatusEnum, page: Int): List<Group>

    fun members(group: Group, page: Int): List<Member>
}