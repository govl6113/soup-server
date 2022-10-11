package com.github.soup.group.participant.application.facade

import com.github.soup.group.application.service.GroupServiceImpl
import com.github.soup.group.domain.Group
import com.github.soup.group.participant.application.service.ParticipantServiceImpl
import com.github.soup.group.participant.infra.http.request.CreateParticipantRequest
import com.github.soup.member.application.service.MemberServiceImpl
import com.github.soup.member.domain.Member
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
}