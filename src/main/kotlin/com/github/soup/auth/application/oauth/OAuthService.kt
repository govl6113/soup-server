package com.github.soup.auth.application.oauth

import com.github.soup.auth.domain.auth.AuthType

interface OAuthService {
	fun getClientId(type: AuthType, token: String): String
}