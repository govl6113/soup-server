package com.github.soup.auth.infra.http.request

import com.github.soup.auth.domain.auth.AuthType
import javax.validation.constraints.NotNull

data class SignInRequest(
	@NotNull
	val type: AuthType,

	@NotNull
	val token: String,
)