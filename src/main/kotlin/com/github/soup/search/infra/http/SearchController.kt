package com.github.soup.search.infra.http

import com.github.soup.search.application.facade.SearchFacadeImpl
import com.github.soup.search.application.service.SearchServiceImpl
import com.github.soup.search.infra.http.request.SearchRequest
import com.github.soup.search.infra.http.request.SearchType
import com.github.soup.search.infra.http.response.SearchResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/search")
class SearchController(
    private val searchFacade: SearchFacadeImpl
) {
    @GetMapping("/{type}")
    fun search(
        @PathVariable("type") type: SearchType,
        @RequestBody @Valid request: SearchRequest
    ): ResponseEntity<SearchResponse> {
        return ResponseEntity.ok().body(
            searchFacade.searchGroupAndUser(type, request)
        )
    }
}