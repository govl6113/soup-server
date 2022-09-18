package com.github.soup.auth.infra.persistence.auth

import com.github.soup.auth.domain.auth.Auth
import com.github.soup.auth.domain.auth.AuthType
import org.springframework.data.jpa.repository.JpaRepository

interface AuthJpaRepository : JpaRepository<Auth, String> {
	fun findByMemberId(memberId: String): Auth?
	fun findByTypeAndClientId(type: AuthType, clientId: String): Auth?
}