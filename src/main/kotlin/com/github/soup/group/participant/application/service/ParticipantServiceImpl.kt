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

    override fun getParticipantList(group: Group, page: Int): List<Participant> {
        val pageable: Pageable = PageRequest.of(page - 1, 10)
        return participantRepository.getByGroupAndIsAccepted(
            group = group,
            pageable = pageable,
            isAccepted = null
        )
    }

    override fun getByMemberIdAndGroup(memberId: String, group: Group): Participant? {
        return participantRepository.getByMemberIdAndGroup(memberId, group)
    }

    override fun checkParticipant(member: Member, group: Group) {
        if (participantRepository.getByMemberAndGroupAndIsAccepted(member, group, true) == null)
            throw NotParticipantException()
    }

    // group에서 사용
    override fun joinGroupList(member: Member, status: GroupStatusEnum, page: Int): List<Group> {
        val pageable = PageRequest.of(page - 1, 10)
        return participantRepository.getJoinList(member = member, status = status, pageable = pageable)
    }

    override fun members(group: Group, page: Int): List<Member> {
        val pageable: Pageable = PageRequest.of(page - 1, 10)
        return participantRepository.getByGroupAndIsAccepted(
            group = group,
            pageable = pageable,
            isAccepted = true
        ).map { it.member }
    }

    override fun getParticipantCount(group: Group): Int {
        return participantRepository.getParticipantCount(group)
    }

}