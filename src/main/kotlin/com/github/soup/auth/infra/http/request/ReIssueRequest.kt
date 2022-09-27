package com.github.soup.auth.infra.http.request

import javax.validation.constraints.NotBlank

data class ReIssueRequest(
	@NotBlank
	val accessToken: String,

	@NotBlank
	val refreshToken: String
)