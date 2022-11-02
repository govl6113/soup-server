package com.github.soup.follow.application.facade

import com.github.soup.follow.application.service.FollowServiceImpl
import com.github.soup.follow.domain.Follow
import com.github.soup.follow.exception.NotFoundFollowAuthorityException
import com.github.soup.follow.infra.http.response.FollowResponse
import com.github.soup.member.application.service.MemberServiceImpl
import com.github.soup.member.domain.Member
import com.github.soup.member.infra.http.response.MemberResponse
import org.springframework.stereotype.Component

@Component
class FollowFacadeImpl(
    private val followService: FollowServiceImpl,
    private val memberService: MemberServiceImpl,
) : FollowFacade {

    override fun create(fromId: String, targetId: String): FollowResponse {
        val from: Member = memberService.getByMemberId(fromId)
        val to: Member = memberService.getByMemberId(targetId)

        return followService.save(
            Follow(
                from = from,
                to = to
            )
        ).toResponse()
    }

    override fun getFromList(memberId: String): List<MemberResponse> {
        val from: Member = memberService.getByMemberId(memberId)
        return followService.getFromList(from).map { f -> f.from.toResponse() }
    }

    override fun getToList(memberId: String): List<MemberResponse> {
        val to: Member = memberService.getByMemberId(memberId)
        return followService.getToList(to).map { f -> f.to.toResponse() }
    }

    override fun delete(memberId: String, followId: String): Boolean {
        val member: Member = memberService.getByMemberId(memberId)
        val follow: Follow = followService.getByMemberAndTo(member, followId)

        if (!member.id.equals(follow.from.id)) {
            throw NotFoundFollowAuthorityException()
        }
        followService.delete(follow)
        return true
    }

}