package com.github.soup.group.infra.http

import com.github.soup.group.application.facade.GroupFacadeImpl
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.domain.GroupTypeEnum
import com.github.soup.group.infra.http.request.CreateGroupRequest
import com.github.soup.group.infra.http.request.ListGroupRequest
import com.github.soup.group.infra.http.request.UpdateGroupRequest
import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.member.infra.http.response.MemberResponse
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

@RestController
@RequestMapping("/api/group")
class GroupController(
    private val groupFacade: GroupFacadeImpl
) {
    @ApiOperation(value = "그룹 생성")
    @PostMapping("/new")
    fun creatGroup(
        @ApiIgnore authentication: Authentication,
        @Valid request: CreateGroupRequest
    ): ResponseEntity<GroupResponse> =
        ResponseEntity.ok().body(
            groupFacade.create(
                authentication.name,
                request
            )
        )

    @ApiOperation(value = "그룹 조회")
    @GetMapping("/{groupId}")
    fun getGroup(
        @ApiIgnore authentication: Authentication,
        @PathVariable("groupId") groupId: String,
    ): ResponseEntity<GroupResponse> =
        ResponseEntity.ok().body(
            groupFacade.get(
                authentication.name,
                groupId
            )
        )

    @ApiOperation(value = "그룹 수정")
    @PutMapping("/{groupId}")
    fun updateGroup(
        @ApiIgnore authentication: Authentication,
        @PathVariable("groupId") groupId: String,
        @Valid request: UpdateGroupRequest
    ): ResponseEntity<GroupResponse> =
        ResponseEntity.ok().body(
            groupFacade.update(
                authentication.name,
                groupId,
                request
            )
        )

    @ApiOperation(value = "그룹 상태 시작")
    @PatchMapping("/{groupId}/start")
    fun startGroup(
        @ApiIgnore authentication: Authentication,
        @PathVariable("groupId") groupId: String,
    ): ResponseEntity<GroupResponse> =
        ResponseEntity.ok().body(
            groupFacade.start(
                authentication.name,
                groupId
            )
        )

    @ApiOperation(value = "그룹 상태 종료")
    @PatchMapping("/{groupId}/finish")
    fun finishGroup(
        @ApiIgnore authentication: Authentication,
        @PathVariable("groupId") groupId: String,
    ): ResponseEntity<GroupResponse> =
        ResponseEntity.ok().body(
            groupFacade.finish(
                authentication.name,
                groupId
            )
        )

    @ApiOperation(value = "모든 그룹 목록")
    @GetMapping("/list/{type}")
    fun allGroup(
        @PathVariable("type") type: GroupTypeEnum,
        @RequestParam(value = "page", required = false, defaultValue = "1") page: Int,
        @RequestParam(value = "status", required = false) status: GroupStatusEnum?,
        @RequestParam(value = "online", required = false) online: Boolean?,
        @RequestParam(value = "minPersonnel", required = false) minPersonnel: Int?,
        @RequestParam(value = "maxPersonnel", required = false) maxPersonnel: Int?,
    ): ResponseEntity<List<GroupResponse>> =
        ResponseEntity.ok().body(
            groupFacade.allGroups(
                ListGroupRequest(type, page, status, online, minPersonnel, maxPersonnel),
            )
        )

    @ApiOperation(value = "참여 그룹 목록")
    @GetMapping("/{memberId}/list/join")
    fun joinList(
        @PathVariable("memberId") memberId: String,
        @RequestParam(value = "page", required = false, defaultValue = "1") page: Int,
        @RequestParam(value = "status", required = true, defaultValue = "PROGRESS") status: GroupStatusEnum,
    ): ResponseEntity<List<GroupResponse>> {
        return ResponseEntity.ok().body(
            groupFacade.joinGroups(
                memberId = memberId,
                status = status,
                page = page
            )
        )
    }

    @ApiOperation(value = "참여자 목록")
    @GetMapping("/{groupId}/members")
    fun members(
        @ApiIgnore authentication: Authentication,
        @PathVariable("groupId") groupId: String,
        @RequestParam(value = "page", required = false, defaultValue = "1") page: Int,
    ): ResponseEntity<List<MemberResponse>> {
        return ResponseEntity.ok().body(
            groupFacade.members(
                memberId = authentication.name,
                groupId = groupId,
                page = page
            )
        )
    }

    @ApiOperation(value = "인기 그룹 목록")
    @GetMapping("/popularity")
    fun popularity(): ResponseEntity<List<GroupResponse>> = ResponseEntity.ok().body(groupFacade.popularity())


    @ApiOperation(value = "참여 중인 인원 확인")
    @GetMapping("/{groupId}/count")
    fun getParticipantCount(
        @PathVariable("groupId") groupId: String
    ): ResponseEntity<Int> =
        ResponseEntity.ok().body(
            groupFacade.getParticipantCount(groupId)
        )
}
