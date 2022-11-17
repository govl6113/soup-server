package com.github.soup.group.participant.infra.persistence

import com.github.soup.file.domain.QFile.file
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
            .leftJoin(participant.group, group)
            .leftJoin(group.image, file)
            .where(
                participant.member.eq(member),
                stateEq(status)
            )
            .orderBy(participant.group.createdAt.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
    }

    override fun getParticipantCount(group: Group): Int {
        return Math.toIntExact(
            queryFactory
                .select(participant.count())
                .from(participant)
                .where(
                    participant.isAccepted.eq(true)
                )
                .fetchFirst()
        )
    }

    private fun stateEq(status: GroupStatusEnum?): BooleanExpression? {
        return if (status != null) group.status.eq(status)
        else null
    }

}