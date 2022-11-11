package com.github.soup.scrap.application.facade

import com.github.soup.group.application.service.GroupServiceImpl
import com.github.soup.group.domain.Group
import com.github.soup.member.application.service.MemberServiceImpl
import com.github.soup.member.domain.Member
import com.github.soup.scrap.application.service.ScrapServiceImpl
import com.github.soup.scrap.domain.Scrap
import com.github.soup.scrap.exception.NotFoundScrapAuthorityException
import com.github.soup.scrap.exception.NotFoundScrapException
import com.github.soup.scrap.infra.http.request.CreateScrapRequest
import com.github.soup.scrap.infra.http.response.ScrapResponse
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class ScrapFacadeImpl(
    private val scrapService: ScrapServiceImpl,
    private val memberService: MemberServiceImpl,
    private val groupService: GroupServiceImpl
) : ScrapFacade {
    
    override fun create(memberId: String, request: CreateScrapRequest): ScrapResponse {
        val member: Member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(request.groupId)

        return try {
            scrapService.getByMemberAndGroup(member, group)!!.toResponse()
        } catch (e: NotFoundScrapException) {
            scrapService.save(
                Scrap(
                    member = member,
                    group = group
                )
            ).toResponse()
        }
    }

    override fun getList(memberId: String): List<ScrapResponse> {
        val member: Member = memberService.getByMemberId(memberId)
        return scrapService.getByMember(member).map { s -> s.toResponse() }
    }

    override fun delete(memberId: String, scrapId: String): Boolean {
        val member: Member = memberService.getByMemberId(memberId)
        val scrap: Scrap = scrapService.getById(scrapId)

        if (member.id != scrap.member.id) {
            throw NotFoundScrapAuthorityException()
        }

        scrapService.delete(scrap)
        return true
    }
}