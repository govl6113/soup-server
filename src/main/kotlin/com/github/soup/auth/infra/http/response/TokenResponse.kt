package com.github.soup.auth.infra.http.response

data class TokenResponse(
	val accessToken: String,
	val refreshToken: String,
)