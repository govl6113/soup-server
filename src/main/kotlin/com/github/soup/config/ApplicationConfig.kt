package com.github.soup.config

import com.github.soup.auth.application.oauth.OAuthService
import com.github.soup.auth.application.oauth.OAuthServiceImpl
import com.github.soup.auth.application.oauth.OAuthServiceMock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ApplicationConfig {

	@Bean
	@Profile("!local")
	fun oAuthServiceImpl(): OAuthService = OAuthServiceImpl()

	@Bean
	@Profile("local")
	fun oAuthServiceMock(): OAuthService = OAuthServiceMock()
}