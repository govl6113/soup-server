package com.github.soup.search.application.service

import com.github.soup.group.domain.Group
import com.github.soup.member.domain.Member
import com.github.soup.search.infra.http.request.SearchRequest
import com.github.soup.search.infra.http.response.SearchResponse
import org.springframework.data.domain.Page

interface SearchService {

    fun searchGroup(request: SearchRequest): Page<Group>

    fun searchUser(request: SearchRequest): Page<Member>
}