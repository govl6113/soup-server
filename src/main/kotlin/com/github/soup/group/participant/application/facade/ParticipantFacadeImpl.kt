package com.github.soup.group.participant.application.facade

import com.github.soup.group.application.service.GroupServiceImpl
import com.github.soup.group.domain.Group
import com.github.soup.group.exception.NotFoundManagerAuthorityException
import com.github.soup.group.participant.application.service.ParticipantServiceImpl
import com.github.soup.group.participant.exception.NotParticipantException
import com.github.soup.group.participant.infra.http.request.AcceptParticipantRequest
import com.github.soup.group.participant.infra.http.request.CreateParticipantRequest
import com.github.soup.member.application.service.MemberServiceImpl
import com.github.soup.member.domain.Member
import com.github.soup.member.infra.http.response.MemberResponse
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class ParticipantFacadeImpl(
    private val participantService: ParticipantServiceImpl,
    private val memberService: MemberServiceImpl,
    private val groupService: GroupServiceImpl
) : ParticipantFacade {

    @Transactional
    override fun join(memberId: String, request: CreateParticipantRequest): Boolean {
        val member: Member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(request.groupId)

        if (group.recruitment == GroupRecruitmentEnum.FIRSTCOME) {
            participantService.save(
                group = group,
                member = member,
                isAccepted = true,
                message = request.message
            )
        }
        if (group.recruitment == GroupRecruitmentEnum.SELECTION) {
            participantService.save(
                group = group,
                member = member,
                isAccepted = false,
                message = request.message
            )
        }
        return true
    }

    override fun participantList(memberId: String, groupId: String, page: Int): List<MemberResponse> {
        val member: Member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(groupId)

        if (!member.id.equals(group.manager.id)) {
            throw NotFoundManagerAuthorityException()
        }
        return participantService.getParticipantList(group, page).map { member.toResponse() }
    }

    @Transactional
    override fun accept(memberId: String, groupId: String, request: AcceptParticipantRequest): Boolean {
        val member: Member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(groupId)

        if (!member.id.equals(group.manager.id)) {
            throw NotFoundManagerAuthorityException()
        }

        request.memberIdList.forEach { participantService.getByMemberIdAndGroup(it, group)?.isAccepted = true }
        return true
    }

    @Transactional
    override fun isParticipant(memberId: String, groupId: String): Boolean {
        val member: Member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(groupId)

        return try {
            participantService.checkParticipant(member, group)
            true
        } catch (e: NotParticipantException) {
            false
        }
    }
}