package com.github.soup.search.infra.http.response

import com.github.soup.group.domain.Group
import com.github.soup.member.domain.Member
import org.springframework.data.domain.Page

data class SearchResponse(

    val groupList: List<Group>?,

    val memberList: List<Member>?

    )
