package com.github.soup.group.participant.infra.persistence

import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.domain.QGroup.group
import com.github.soup.group.participant.domain.Participant
import com.github.soup.group.participant.domain.ParticipantRepository
import com.github.soup.group.participant.domain.QParticipant.participant
import com.github.soup.member.domain.Member
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ParticipantRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val participantRepository: ParticipantJpaRepository
) : ParticipantRepository {

    override fun save(participant: Participant): Participant {
        return participantRepository.save(participant)
    }

    override fun participant(member: Member, group: Group): Participant? {
        return participantRepository.findByMemberAndGroupAndIsAccepted(member, group, true)
    }

    override fun getMembers(group: Group, pageable: Pageable): List<Participant> {
        return participantRepository.findByGroupAndIsAccepted(group, true, pageable)
    }

    override fun getJoinList(member: Member, status: GroupStatusEnum, pageable: Pageable): List<Group> {
        return queryFactory
            .select(participant.group)
            .from(participant)
            .join(participant.group, group)
            .on(
                participant.member.eq(member),
                stateEq(status)
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
    }

    private fun stateEq(status: GroupStatusEnum?): BooleanExpression? {
        return if (status != null) group.status.eq(status)
        else null
    }
}