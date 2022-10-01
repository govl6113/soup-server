package com.github.soup.member.infra.http

import com.github.soup.member.exception.NotFoundMemberException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemberExceptionController {
    @ExceptionHandler(value = [NotFoundMemberException::class])
    fun notFoundMemberException(exception: NotFoundMemberException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

}
