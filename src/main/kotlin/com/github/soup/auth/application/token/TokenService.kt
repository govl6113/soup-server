package com.github.soup.auth.application.token

import com.github.soup.auth.domain.auth.Auth
import com.github.soup.auth.domain.token.Token
import org.springframework.security.core.Authentication

interface TokenService {
	fun create(auth: Auth): Token
	fun remove(auth: Auth): Boolean
	fun validation(token: String): Boolean
	fun parse(token: String): String
	fun getAuthentication(token: String): Authentication
}