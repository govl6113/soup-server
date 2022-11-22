package com.github.soup.group.participant.infra.persistence

import com.github.soup.group.domain.Group
import com.github.soup.group.participant.domain.Participant
import com.github.soup.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipantJpaRepository : JpaRepository<Participant, String> {

    fun findByMemberIdAndGroup(memberId: String, group: Group): Participant?
}