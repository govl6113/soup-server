package com.github.soup.group.participant.infra.http

import com.github.soup.group.participant.exception.NotParticipantException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ParticipantExceptionController {

    @ExceptionHandler(value = [NotParticipantException::class])
    fun notParticipantException(exception: NotParticipantException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }
}