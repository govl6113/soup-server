package com.github.soup.search.application.service

import com.github.soup.group.domain.Group
import com.github.soup.group.infra.persistence.GroupRepositoryImpl
import com.github.soup.member.domain.Member
import com.github.soup.member.infra.persistence.MemberRepositoryImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SearchServiceImpl(
    private val groupRepository: GroupRepositoryImpl,
    private val memberRepository: MemberRepositoryImpl,
) : SearchService {

    override fun searchGroup(page: Int, keyword: String): List<Group> {
        val pageable: Pageable = PageRequest.of(page-1, 10)
        return groupRepository.searchGroup(
            name = keyword,
            pageable = pageable
        )
    }

    override fun searchUser(page: Int, keyword: String): List<Member> {
        val pageable: Pageable = PageRequest.of(page-1, 10)
        return  memberRepository.searchMember(
            name = keyword,
            pageable = pageable
        )
    }
}
