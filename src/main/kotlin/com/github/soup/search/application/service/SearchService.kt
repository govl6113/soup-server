package com.github.soup.search.application.service

import com.github.soup.group.domain.Group
import com.github.soup.member.domain.Member
import org.springframework.data.domain.Page

interface SearchService {

    fun searchGroup(page: Int, keyword: String): List<Group>
    fun searchUser(page: Int, keyword: String): List<Member>
}