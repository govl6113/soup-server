package com.github.soup.group.application.facade

import com.github.soup.file.application.facade.FileFacadeImpl
import com.github.soup.file.domain.File
import com.github.soup.file.domain.FileType
import com.github.soup.group.application.service.GroupServiceImpl
import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.exception.NotFoundManagerAuthorityException
import com.github.soup.group.infra.http.request.CreateGroupRequest
import com.github.soup.group.infra.http.request.ListGroupRequest
import com.github.soup.group.infra.http.request.UpdateGroupRequest
import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.group.participant.application.service.ParticipantServiceImpl
import com.github.soup.member.application.service.MemberServiceImpl
import com.github.soup.member.domain.Member
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class GroupFacadeImpl(
    private val groupService: GroupServiceImpl,
    private val memberService: MemberServiceImpl,
    private val fileFacade: FileFacadeImpl,
    private val participantService: ParticipantServiceImpl
) : GroupFacade {

    @Transactional
    override fun create(memberId: String, request: CreateGroupRequest): GroupResponse {
        val manager: Member = memberService.getByMemberId(memberId)

        val file: File? = request.image?.let {
            fileFacade.upload(memberId = memberId, type = FileType.GROUP, image = it)
        }
        val group = groupService.save(
            request.toEntity(manager = manager, image = file)
        )
        participantService.save(group = group, member = manager, isAccepted = true, message = "")
        return group.toResponse()
    }

    @Transactional
    override fun update(memberId: String, groupId: String, request: UpdateGroupRequest): GroupResponse {
        val member: Member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(groupId)
        if (member != group.manager)
            throw NotFoundManagerAuthorityException()

        return group.update(request = request).toResponse()
    }

    override fun get(memberId: String, groupId: String): GroupResponse {
        return groupService.getById(groupId).toResponse()
    }

    @Transactional
    override fun finish(memberId: String, groupId: String): GroupResponse {
        val member: Member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(groupId)
        if (member != group.manager) {
            throw NotFoundManagerAuthorityException()
        }
        group.status = GroupStatusEnum.FINISH
        return group.toResponse()
    }

    override fun allGroups(request: ListGroupRequest): List<GroupResponse> {
        return groupService.allGroupList(request).map { it.toResponse() }
    }

    override fun joinGroups(memberId: String, status: GroupStatusEnum, page: Int): List<GroupResponse> {
        val member = memberService.getByMemberId(memberId)
        return participantService.joinGroupList(member = member, status = status, page = page).map { it.toResponse() }
    }

    override fun members(memberId: String, groupId: String, page: Int): List<Member> {
        val member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(groupId)
        participantService.checkParticipant(member = member, group = group)
        return participantService.members(group = group, page = page)
    }
}