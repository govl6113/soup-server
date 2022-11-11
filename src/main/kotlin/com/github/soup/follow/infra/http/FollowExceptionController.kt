package com.github.soup.follow.infra.http

import com.github.soup.follow.exception.NotFollowSelfException
import com.github.soup.follow.exception.NotFoundFollowAuthorityException
import com.github.soup.follow.exception.NotFoundFollowException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class FollowExceptionController {

    @ExceptionHandler(value = [NotFoundFollowException::class])
    fun notFoundFollowException(exception: NotFoundFollowException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(value = [NotFoundFollowAuthorityException::class])
    fun notFoundFollowAuthorityException(exception: NotFoundFollowAuthorityException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(value = [NotFollowSelfException::class])
    fun notFollowSelfException(exception: NotFollowSelfException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }
}