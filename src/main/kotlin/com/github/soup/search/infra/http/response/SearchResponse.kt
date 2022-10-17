package com.github.soup.search.infra.http.response

import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.member.infra.http.response.MemberResponse

sealed class SearchResponse {

    data class Group(
        val group: GroupResponse
    ) : SearchResponse()

    data class Member(
        val member: MemberResponse
    ) : SearchResponse()

}
