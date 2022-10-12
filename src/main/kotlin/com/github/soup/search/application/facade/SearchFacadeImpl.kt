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

    override fun searchGroupAndUser(type: SearchType, page: Int, keyword: String): List<SearchResponse> {
        return when (type) {
            SearchType.GROUP -> {
                SearchService.searchGroup(page, keyword).map {
                    SearchResponse.Group(it.toResponse())
                }
            }
            SearchType.MEMBER -> {
                SearchService.searchUser(page, keyword).map {
                    SearchResponse.Member(it.toResponse())
                }
            }
        }
    }

}