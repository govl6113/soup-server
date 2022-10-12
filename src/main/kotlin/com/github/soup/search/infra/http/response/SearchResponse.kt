package com.github.soup.search.infra.http.response

import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.member.infra.http.response.MemberResponse

sealed class SearchResponse {

    data class GroupList(
        val groups: List<GroupResponse>
    ) : SearchResponse()

    data class MemberList(
        val members: List<MemberResponse>
    ) : SearchResponse()

}
