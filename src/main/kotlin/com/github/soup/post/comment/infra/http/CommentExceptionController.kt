package com.github.soup.post.comment.infra.http

import com.github.soup.post.comment.exception.NotFoundCommentAuthorityException
import com.github.soup.post.comment.exception.NotFoundCommentException
import com.github.soup.post.exception.NotFoundPostAuthorityException
import com.github.soup.post.exception.NotFoundPostException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommentExceptionController {

    @ExceptionHandler(value = [NotFoundCommentException::class])
    fun notFoundCommentException(exception: NotFoundCommentException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(value = [NotFoundCommentAuthorityException::class])
    fun notFoundCommentAuthorityException(exception: NotFoundCommentAuthorityException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.message)
    }
}