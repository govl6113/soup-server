package com.github.soup.auth.application.auth

import com.github.soup.auth.infra.http.request.ReIssueRequest
import com.github.soup.auth.infra.http.request.SignInRequest
import com.github.soup.auth.infra.http.request.SignUpRequest
import com.github.soup.auth.infra.http.response.TokenResponse

interface AuthService {
	fun login(request: SignInRequest): TokenResponse
	fun create(request: SignUpRequest): TokenResponse
	fun reissue(request: ReIssueRequest): TokenResponse
	fun logout(memberId: String): Boolean
}