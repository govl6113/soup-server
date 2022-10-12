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
) : SearchFacade {

    override fun searchGroupAndUser(type: SearchType, page: Int, keyword: String): SearchResponse {
        return when (type) {
            SearchType.GROUP -> {
                SearchResponse.GroupList(
                    SearchService.searchGroup(page, keyword).map { it.toResponse() }
                )
            }
            SearchType.MEMBER -> {
                SearchResponse.MemberList(
                    SearchService.searchUser(page, keyword).map { it.toResponse() }
                )
            }
        }
    }

}