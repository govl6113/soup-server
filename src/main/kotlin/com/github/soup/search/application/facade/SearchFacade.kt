package com.github.soup.search.application.facade

import com.github.soup.search.infra.http.request.SearchType
import com.github.soup.search.infra.http.response.SearchResponse

interface SearchFacade {
    fun searchGroupAndUser(type: SearchType, page: Int, keyword: String): List<SearchResponse>
}