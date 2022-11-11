package com.github.soup.review.infra.http

import com.github.soup.review.exception.NotFoundReviewException
import com.github.soup.review.exception.NotWriterException
import com.github.soup.review.exception.ReviewDontSeeSelfException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ReviewExceptionController {

    @ExceptionHandler(value = [NotFoundReviewException::class])
    fun notFoundReviewException(exception: NotFoundReviewException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(value = [NotWriterException::class])
    fun notWriterException(exception: NotWriterException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.message)
    }

    @ExceptionHandler(value = [ReviewDontSeeSelfException::class])
    fun reviewDontSeeSelfException(exception: ReviewDontSeeSelfException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }
}