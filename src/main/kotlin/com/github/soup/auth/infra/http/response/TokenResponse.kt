package com.github.soup.auth.infra.http.response

class TokenResponse(
	val accessToken: String,
	val refreshToken: String,
)