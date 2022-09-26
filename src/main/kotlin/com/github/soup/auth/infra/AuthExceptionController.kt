package com.github.soup.auth.infra

import com.github.soup.auth.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionController {
	@ExceptionHandler(value = [AlreadyExistingAuthException::class])
	fun alreadyExistingAuthException(exception: AlreadyExistingAuthException): ResponseEntity<String> {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
	}

	@ExceptionHandler(value = [InvalidOAuthException::class])
	fun invalidOAuthException(exception: InvalidOAuthException): ResponseEntity<String> {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
	}

	@ExceptionHandler(value = [InvalidTokenException::class])
	fun invalidTokenException(exception: InvalidTokenException): ResponseEntity<String> {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
	}

	@ExceptionHandler(value = [NotFoundAuthException::class])
	fun notFoundAuthException(exception: NotFoundAuthException): ResponseEntity<String> {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
	}

	@ExceptionHandler(value = [NotFoundRoleException::class])
	fun notFoundRoleException(exception: NotFoundRoleException): ResponseEntity<String> {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
	}
}