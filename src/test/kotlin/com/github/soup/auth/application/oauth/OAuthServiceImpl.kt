package com.github.soup.auth.application.oauth

import com.github.soup.auth.domain.auth.AuthType
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(value = ["test"])
class OAuthServiceImpl : OAuthService {
	override fun getClientId(type: AuthType, token: String) = token.replace("-", "").substring(0, 10)
}