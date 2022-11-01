package com.github.soup.auth.application.oauth

import com.github.soup.auth.domain.auth.AuthType

class OAuthServiceMock : OAuthService {
	override fun getClientId(type: AuthType, token: String): String {
		return token.replace("-", "").substring(0, 10)
	}
}