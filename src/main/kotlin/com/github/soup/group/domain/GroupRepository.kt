package com.github.soup.group.domain

import com.github.soup.group.infra.http.request.ListGroupRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface GroupRepository {

    fun save(group: Group): Group

    fun getById(groupId: String): Optional<Group>

    fun delete(group: Group)

    fun getList(condition: ListGroupRequest, pageable: Pageable): List<Group>

    fun searchGroup(name: String, pageable: Pageable): List<Group>
}