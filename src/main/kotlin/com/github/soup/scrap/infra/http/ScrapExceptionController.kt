package com.github.soup.scrap.infra.http

import com.github.soup.scrap.exception.NotFoundScrapAuthorityException
import com.github.soup.scrap.exception.NotFoundScrapException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ScrapExceptionController {
    @ExceptionHandler(value = [NotFoundScrapException::class])
    fun notFoundScrapException(exception: NotFoundScrapException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(value = [NotFoundScrapAuthorityException::class])
    fun notFoundScrapAuthorityException(exception: NotFoundScrapAuthorityException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.message)
    }
}