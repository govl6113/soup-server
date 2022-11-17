package com.github.soup.group.participant.application.service

import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.participant.domain.Participant
import com.github.soup.group.participant.exception.NotParticipantException
import com.github.soup.group.participant.infra.persistence.ParticipantRepositoryImpl
import com.github.soup.member.domain.Member
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ParticipantServiceImpl(
    private val participantRepository: ParticipantRepositoryImpl,
) : ParticipantService {

    @Transactional
    override fun save(group: Group, member: Member, isAccepted: Boolean, message: String): Boolean {
        participantRepository.save(
            Participant(
                group = group,
                member = member,
                isAccepted = isAccepted,
                message = message
            )
        )

        return true
    }

    override fun checkParticipant(member: Member, group: Group) {
        if (participantRepository.participant(member, group) == null)
            throw NotParticipantException()
    }

    override fun joinGroupList(member: Member, status: GroupStatusEnum, page: Int): List<Group> {
        val pageable = PageRequest.of(page - 1, 10)
        return participantRepository.getJoinList(member = member, status = status, pageable = pageable)
    }

    override fun members(group: Group, page: Int): List<Member> {
        val pageable: Pageable = PageRequest.of(page - 1, 10)
        return participantRepository.getMembers(group = group, pageable = pageable).map { it.member }
    }

    override fun getParticipantCount(group: Group): Int {
        return participantRepository.getParticipantCount(group)
    }
}