package com.github.soup.search.infra.http.request

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import javax.validation.constraints.NotEmpty

data class SearchRequest(
    @PageableDefault(page = 0, size = 10, sort = ["name"], direction = Sort.Direction.DESC)
    val pageable: Pageable,

    @NotEmpty
    val keyword: String
)