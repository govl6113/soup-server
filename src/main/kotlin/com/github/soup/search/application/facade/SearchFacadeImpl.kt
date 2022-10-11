package com.github.soup.search.application.facade

import com.github.soup.group.domain.Group
import com.github.soup.member.domain.Member
import com.github.soup.search.application.service.SearchServiceImpl
import com.github.soup.search.infra.http.request.SearchRequest
import com.github.soup.search.infra.http.request.SearchType
import com.github.soup.search.infra.http.response.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class SearchFacadeImpl(
    private val SearchService: SearchServiceImpl
): SearchFacade {

    override fun searchGroupAndUser(type: SearchType, request: SearchRequest): SearchResponse {
        return when (type) {
            SearchType.GROUP -> {
                SearchResponse(groupList = SearchService.searchGroup(request), memberList = null)
            }
            SearchType.MEMBER -> {
                SearchResponse(groupList = null, memberList = SearchService.searchUser(request))
            }
        }
    }

}