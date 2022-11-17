package com.github.soup.group.participant.infra.persistence

import com.github.soup.file.domain.QFile.file
import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.domain.QGroup
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

    override fun getByMemberIdAndGroup(memberId: String, group: Group): Participant? {
        return participantRepository.findByMemberIdAndGroup(memberId, group)
    }

    override fun getByGroupAndIsAccepted(group: Group, pageable: Pageable, isAccepted: Boolean?): List<Participant> {
        return queryFactory
            .selectFrom(participant)
            .leftJoin(participant.group, QGroup.group)
            .leftJoin(QGroup.group.image, file)
            .where(
                participant.group.eq(group),
                isAcceptedEq(isAccepted)
            )
            .orderBy(participant.group.createdAt.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
    }

    override fun getByMemberAndGroupAndIsAccepted(member: Member, group: Group, isAccepted: Boolean): Participant? {
        return participantRepository.findByMemberAndGroupAndIsAccepted(member, group, isAccepted)
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

    private fun isAcceptedEq(isAccepted: Boolean?): BooleanExpression? {
        return if (isAccepted != null) participant.isAccepted.eq(isAccepted)
        else null
    }
}