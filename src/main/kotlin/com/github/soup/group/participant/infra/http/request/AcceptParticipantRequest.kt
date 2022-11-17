package com.github.soup.group.participant.infra.http.request

import javax.validation.constraints.NotNull

data class AcceptParticipantRequest(
    @NotNull
    val memberIdList: List<String>
)