package com.github.soup.auth.domain.token

interface TokenRepository {
	fun getByKey(key: String): Token?
	fun save(key: String, token: Token, timeToLive: Long): Token
	fun delete(key: String)
}