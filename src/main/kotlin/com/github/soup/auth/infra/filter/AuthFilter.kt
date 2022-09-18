package com.github.soup.auth.infra.filter

import com.github.soup.auth.application.token.TokenServiceImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthFilter(
	private val tokenService: TokenServiceImpl
) : OncePerRequestFilter() {
	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val token = resolveToken(request)
		if (StringUtils.hasText(token) && tokenService.validation(token as String)) {
			SecurityContextHolder.getContext().authentication = tokenService.getAuthentication(token)
		}
		filterChain.doFilter(request, response)
	}

	private fun resolveToken(request: HttpServletRequest): String? {
		val token = request.getHeader("Authorization")
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return token.substring(7)
		}
		return null
	}
}