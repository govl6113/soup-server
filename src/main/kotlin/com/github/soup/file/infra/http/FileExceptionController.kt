package com.github.soup.file.infra.http

import com.github.soup.file.exception.NotSupportedFileFormatException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class FileExceptionController {

    @ExceptionHandler(value = [NotSupportedFileFormatException::class])
    fun notSupportedFileFormatException(exception: NotSupportedFileFormatException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }
}
