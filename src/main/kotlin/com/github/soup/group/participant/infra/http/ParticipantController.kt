package com.github.soup.group.participant.infra.http

import com.github.soup.group.participant.application.facade.ParticipantFacadeImpl
import com.github.soup.group.participant.infra.http.request.CreateParticipantRequest
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

@RestController
@RequestMapping("/api/participant")
class ParticipantController(
    private val participantFacade: ParticipantFacadeImpl
) {
    @ApiOperation(value = "참여 신청")
    @PostMapping("/new")
    fun createParticipant(
        @ApiIgnore authentication: Authentication,
        @RequestBody @Valid request: CreateParticipantRequest
    ): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            participantFacade.join(
                authentication.name,
                request
            )
        )
    
}