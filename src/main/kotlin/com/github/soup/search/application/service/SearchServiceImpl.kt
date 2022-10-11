package com.github.soup.search.application.service

import com.github.soup.group.domain.Group
import com.github.soup.group.infra.persistence.GroupRepositoryImpl
import com.github.soup.member.domain.Member
import com.github.soup.member.infra.persistence.MemberRepositoryImpl
import com.github.soup.search.application.service.SearchService
import com.github.soup.search.infra.http.request.SearchRequest
import com.github.soup.search.infra.http.request.SearchType
import com.github.soup.search.infra.http.response.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SearchServiceImpl(
    private val groupRepository: GroupRepositoryImpl,
    private val memberRepository: MemberRepositoryImpl,
) : SearchService {

    override fun searchGroup(request: SearchRequest): Page<Group> {
        return groupRepository.searchGroup(
            name = request.keyword,
            pageable = request.pageable
        )
    }

    override fun searchUser(request: SearchRequest): Page<Member> {
        return  memberRepository.searchMember(
            name = request.keyword,
            pageable = request.pageable
        )
    }
}
