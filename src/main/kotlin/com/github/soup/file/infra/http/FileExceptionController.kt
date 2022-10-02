package com.github.soup.file.infra.http

import com.github.soup.file.exception.NotSupportedFileFormatException
import com.github.soup.file.exception.StorageUploadException
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

    @ExceptionHandler(value = [StorageUploadException::class])
    fun storageUploadException(exception: StorageUploadException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.message)
    }
}
