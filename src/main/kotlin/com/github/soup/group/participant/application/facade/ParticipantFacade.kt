package com.github.soup.group.participant.application.facade

import com.github.soup.group.participant.infra.http.request.CreateParticipantRequest

interface ParticipantFacade {
	fun join(memberId: String, request: CreateParticipantRequest): Boolean

	fun isParticipant(memberId: String, groupId: String): Boolean
}