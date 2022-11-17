package com.github.soup.group.infra.persistence

import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupStatusEnum
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface GroupJpaRepository : JpaRepository<Group, String> {

    fun findByNameContaining(name: String, pageable: Pageable): List<Group>

    fun findByStatusOrderByViewsDesc(status: GroupStatusEnum): List<Group>

}