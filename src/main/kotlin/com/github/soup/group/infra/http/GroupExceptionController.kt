package com.github.soup.group.infra.http

import com.github.soup.group.exception.GroupNameAlreadyExistException
import com.github.soup.group.exception.NotFoundGroupException
import com.github.soup.group.exception.NotFoundManagerAuthorityException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GroupExceptionController {

    @ExceptionHandler(value = [GroupNameAlreadyExistException::class])
    fun groupNameAlreadyExistException(exception: GroupNameAlreadyExistException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(value = [NotFoundManagerAuthorityException::class])
    fun notFoundManagerAuthorityException(exception: NotFoundManagerAuthorityException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.message)
    }

    @ExceptionHandler(value = [NotFoundGroupException::class])
    fun notFoundGroupException(exception: NotFoundGroupException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

}
