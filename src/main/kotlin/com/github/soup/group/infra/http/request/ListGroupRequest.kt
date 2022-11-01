package com.github.soup.group.infra.http.request

import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.domain.GroupTypeEnum

data class ListGroupRequest(
    val type: GroupTypeEnum,
    val page: Int,
    val status: GroupStatusEnum? = null,
    val online: Boolean? = null,
    val minPersonnel: Int? = null,
    val maxPersonnel: Int? = null
)
