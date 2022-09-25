package com.github.soup.auth.domain.auth

interface AuthRepository {
	fun getById(id: String): Auth?
	fun getByMemberId(memberId: String): Auth?
	fun getByAuthTypeAndClientId(type: AuthType, clientId: String): Auth?
	fun save(auth: Auth): Auth
}