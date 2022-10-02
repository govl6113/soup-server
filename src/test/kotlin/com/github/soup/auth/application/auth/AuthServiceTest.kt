package com.github.soup.auth.application.auth

import com.github.soup.auth.application.token.TokenServiceImpl
import com.github.soup.auth.domain.auth.AuthType
import com.github.soup.auth.exceptions.AlreadyExistingAuthException
import com.github.soup.auth.exceptions.NotFoundAuthException
import com.github.soup.auth.infra.http.request.ReIssueRequest
import com.github.soup.auth.infra.http.request.SignInRequest
import com.github.soup.auth.infra.http.request.SignUpRequest
import com.github.soup.auth.infra.http.response.TokenResponse
import com.github.soup.config.EmbeddedRedisConfig
import com.github.soup.member.domain.Member
import com.github.soup.member.domain.SexType
import com.github.soup.member.infra.persistence.MemberRepositoryImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Import(value = [EmbeddedRedisConfig::class])
@SpringBootTest
@Transactional
internal class AuthServiceTest(
	@Autowired private val authService: AuthServiceImpl,
	@Autowired private val tokenService: TokenServiceImpl,
	@Autowired private val memberRepository: MemberRepositoryImpl,
) {
	@Test
	@DisplayName("회원가입을 할 수 있어요.")
	fun signUp() {
		val response = authService.create(
			SignUpRequest(
				type = AuthType.KAKAO,
				token = UUID.randomUUID().toString(),
				name = "test_name",
				nickname = "test_nickname",
				sex = SexType.MALE
			)
		)

		assertThat(response).isNotNull.isInstanceOf(TokenResponse::class.java)

		val member = memberRepository.getById(tokenService.parse(response.accessToken))

		assertThat(member).isInstanceOf(Member::class.java)
		assertThat(member?.name).isInstanceOf(String::class.java).isEqualTo("test_name")
		assertThat(member?.nickname).isInstanceOf(String::class.java).isEqualTo("test_nickname")
		assertThat(member?.sex).isInstanceOf(SexType::class.java).isEqualTo(SexType.MALE)
	}

	@Test
	@DisplayName("회원가입에 실패해요")
	fun signUpFailed() {
		val token = UUID.randomUUID().toString()

		authService.create(
			SignUpRequest(
				type = AuthType.KAKAO,
				token = token,
				name = "test_name",
				nickname = "test_nickname",
				sex = SexType.MALE
			)
		)

		Assertions.assertThrows(AlreadyExistingAuthException::class.java) {
			authService.create(
				SignUpRequest(
					type = AuthType.KAKAO,
					token = token,
					name = "test_name",
					nickname = "test_nickname",
					sex = SexType.MALE
				)
			)
		}

		Assertions.assertThrows(AlreadyExistingAuthException::class.java) {
			authService.create(
				SignUpRequest(
					type = AuthType.KAKAO,
					token = token,
					name = "other_test_name",
					nickname = "other_test_nickname",
					sex = SexType.FEMAIL
				)
			)
		}
	}

	@Test
	@DisplayName("로그인을 할 수 있어요.")
	fun singIn() {
		val token = UUID.randomUUID().toString()

		authService.create(
			SignUpRequest(
				type = AuthType.KAKAO,
				token = token,
				name = "test_name",
				nickname = "test_nickname",
				sex = SexType.MALE
			)
		)

		val response = authService.login(
			SignInRequest(
				type = AuthType.KAKAO,
				token = token,
			)
		)

		assertThat(response).isNotNull.isInstanceOf(TokenResponse::class.java)
	}

	@Test
	@DisplayName("로그인에 실패해요")
	fun signInFailed() {
		Assertions.assertThrows(NotFoundAuthException::class.java) {
			authService.login(
				SignInRequest(
					type = AuthType.KAKAO,
					token = UUID.randomUUID().toString(),
				)
			)
		}
	}

	@Test
	@DisplayName("토큰을 재발급 받을 수 있어요.")
	fun reissue() {
		val token = authService.create(
			SignUpRequest(
				type = AuthType.KAKAO,
				token = UUID.randomUUID().toString(),
				name = "test_name",
				nickname = "test_nickname",
				sex = SexType.MALE
			)
		)

		Thread.sleep(1000)

		val result = authService.reissue(
			ReIssueRequest(
				accessToken = token.accessToken,
				refreshToken = token.refreshToken
			)
		)

		assertThat(result).isNotNull.isInstanceOf(TokenResponse::class.java)
		assertThat(result.accessToken).isNotEmpty.isNotEqualTo(token.accessToken)
		assertThat(result.refreshToken).isNotEmpty.isNotEqualTo(token.refreshToken)
	}
}