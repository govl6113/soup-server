package com.github.soup.auth.application.auth

import com.github.soup.auth.application.oauth.OAuthServiceImpl
import com.github.soup.auth.application.token.TokenServiceImpl
import com.github.soup.auth.domain.auth.Auth
import com.github.soup.auth.exceptions.AlreadyExistingAuthException
import com.github.soup.auth.exceptions.InvalidTokenException
import com.github.soup.auth.exceptions.NotFoundAuthException
import com.github.soup.auth.infra.http.request.ReIssueRequest
import com.github.soup.auth.infra.http.request.SignInRequest
import com.github.soup.auth.infra.http.request.SignUpRequest
import com.github.soup.auth.infra.http.response.TokenResponse
import com.github.soup.auth.infra.persistence.auth.AuthRepositoryImpl
import com.github.soup.member.domain.Member
import com.github.soup.member.infra.persistence.MemberRepositoryImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthServiceImpl(
	private val tokenService: TokenServiceImpl,
	private val oAuthService: OAuthServiceImpl,
	private val authRepository: AuthRepositoryImpl,
	private val memberRepository: MemberRepositoryImpl
) : AuthService {
	@Transactional
	override fun login(request: SignInRequest): TokenResponse {
		val auth = authRepository.getByAuthTypeAndClientId(
			request.type,
			oAuthService.getClientId(request.type, request.token)
		) ?: throw NotFoundAuthException()

		return tokenService.create(auth).toResponse()
	}

	@Transactional
	override fun create(request: SignUpRequest): TokenResponse {
		val clientId = oAuthService.getClientId(request.type, request.token)
		if (authRepository.getByAuthTypeAndClientId(request.type, clientId) != null) {
			throw AlreadyExistingAuthException()
		}

		val member = memberRepository.save(
			Member(
				name = request.name,
				nickName = request.nickName,
				sex = request.sex
			)
		)

		val auth = authRepository.save(
			Auth(
				member = member,
				type = request.type,
				clientId = clientId,
			)
		)

		return tokenService.create(auth).toResponse()
	}

	override fun reissue(request: ReIssueRequest): TokenResponse {
		if (!tokenService.validation(request.refreshToken)) {
			throw InvalidTokenException()
		}

		val auth = authRepository.getById(tokenService.parse(request.refreshToken))
			?: throw NotFoundAuthException()

		return tokenService.create(auth).toResponse()
	}

	override fun logout(memberId: String): Boolean {
		val auth = authRepository.getByMemberId(memberId) ?: throw NotFoundAuthException()

		return tokenService.remove(auth).and(true)
	}
}