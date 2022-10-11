package com.github.soup.group.application.service

import com.github.soup.group.domain.Group
import com.github.soup.group.infra.http.request.ListGroupRequest

interface GroupService {

    fun save(group: Group): Group

    fun getById(groupId: String): Group

    fun delete(group: Group)

    fun allGroupList(request: ListGroupRequest): List<Group>
}