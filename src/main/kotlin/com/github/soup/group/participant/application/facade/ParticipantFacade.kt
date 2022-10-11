package com.github.soup.group.participant.application.facade

import com.github.soup.group.participant.infra.http.request.CreateParticipantRequest
import org.springframework.transaction.annotation.Transactional

interface ParticipantFacade {
    @Transactional
    fun join(memberId: String, request: CreateParticipantRequest): Boolean
}