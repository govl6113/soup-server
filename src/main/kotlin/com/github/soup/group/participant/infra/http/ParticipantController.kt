package com.github.soup.group.participant.infra.http

import com.github.soup.group.participant.application.facade.ParticipantFacadeImpl
import com.github.soup.group.participant.infra.http.request.AcceptParticipantRequest
import com.github.soup.group.participant.infra.http.request.CreateParticipantRequest
import com.github.soup.member.infra.http.response.MemberResponse
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
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

    @ApiOperation(value = "참여 신청 목록")
    @GetMapping("/{groupId}")
    fun participantList(
        @ApiIgnore authentication: Authentication,
        @PathVariable("groupId") groupId: String,
        @RequestParam(value = "page", required = false, defaultValue = "1") page: Int
    ): ResponseEntity<List<MemberResponse>> =
        ResponseEntity.ok().body(
            participantFacade.participantList(
                authentication.name,
                groupId,
                page
            )
        )

    @ApiOperation(value = "참여 신청 수락")
    @PatchMapping("/{groupId}/accept")
    fun accept(
        @ApiIgnore authentication: Authentication,
        @PathVariable("groupId") groupId: String,
        @RequestBody @Valid request: AcceptParticipantRequest
    ): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            participantFacade.accept(
                authentication.name,
                groupId,
                request
            )
        )

    @ApiOperation(value = "참여 여부 확인")
    @GetMapping("/check/{groupId}")
    fun check(
        @ApiIgnore authentication: Authentication,
        @PathVariable("groupId") groupId: String
    ): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            participantFacade.isParticipant(
                authentication.name,
                groupId
            )
        )
}