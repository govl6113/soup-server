package com.github.soup.search.application.facade

import com.github.soup.search.application.service.SearchServiceImpl
import com.github.soup.search.infra.http.request.SearchType
import com.github.soup.search.infra.http.response.SearchResponse
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class SearchFacadeImpl(
    private val SearchService: SearchServiceImpl
): SearchFacade {

    override fun searchGroupAndUser(type: SearchType, page: Int, keyword: String): SearchResponse {
        return when (type) {
            SearchType.GROUP -> {
                SearchResponse(groupList = SearchService.searchGroup(page, keyword), memberList = null)
            }
            SearchType.MEMBER -> {
                SearchResponse(groupList = null, memberList = SearchService.searchUser(page, keyword))
            }
        }
    }

}