package com.github.soup.auth.infra.persistence.auth

import com.github.soup.auth.domain.auth.Auth
import com.github.soup.auth.domain.auth.AuthRepository
import com.github.soup.auth.domain.auth.AuthType
import org.springframework.stereotype.Repository

@Repository
class AuthRepositoryImpl(
	private val authJpaRepository: AuthJpaRepository
) : AuthRepository {
	override fun getById(id: String): Auth? {
		return authJpaRepository.findById(id).orElse(null)
	}

	override fun getByMemberId(memberId: String): Auth? {
		return authJpaRepository.findByMemberId(memberId)
	}

	override fun getByAuthTypeAndClientId(type: AuthType, clientId: String): Auth? {
		return authJpaRepository.findByTypeAndClientId(type, clientId)
	}

	override fun save(auth: Auth): Auth {
		return authJpaRepository.save(auth)
	}
}