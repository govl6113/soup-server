package com.github.soup.auth.application.oauth

import com.github.soup.auth.domain.auth.AuthType
import com.github.soup.auth.exceptions.InvalidOAuthException
import com.github.soup.auth.infra.http.oauth.KakaoTokenResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OAuthServiceImpl : OAuthService {
	override fun getClientId(type: AuthType, token: String): String {
		val template = RestTemplate()

		return when (type) {
			AuthType.Kakao -> {
				val headers = HttpHeaders()
				headers.set("Authorization", "Bearer $token")

				val response = template.exchange(
					"https://kapi.kakao.com/v1/user/access_token_info",
					HttpMethod.GET,
					HttpEntity(null, headers),
					KakaoTokenResponse::class.java
				)

				response.body?.id ?: throw InvalidOAuthException()
			}
		}
	}
}