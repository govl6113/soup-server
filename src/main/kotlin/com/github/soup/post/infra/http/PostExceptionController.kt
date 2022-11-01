package com.github.soup.post.infra.http

import com.github.soup.post.exception.NotFoundPostAuthorityException
import com.github.soup.post.exception.NotFoundPostException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class PostExceptionController {

    @ExceptionHandler(value = [NotFoundPostException::class])
    fun notFoundPostException(exception: NotFoundPostException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(value = [NotFoundPostAuthorityException::class])
    fun notFoundPostAuthorityException(exception: NotFoundPostAuthorityException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.message)
    }
}