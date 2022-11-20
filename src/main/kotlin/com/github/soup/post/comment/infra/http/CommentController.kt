package com.github.soup.post.comment.infra.http

import com.github.soup.post.comment.application.facade.CommentFacadeImpl
import com.github.soup.post.comment.infra.http.request.CreateCommentRequest
import com.github.soup.post.comment.infra.http.response.CommentResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

@RestController
@RequestMapping("/api/comment")
class CommentController(
    private val commentFacade: CommentFacadeImpl
) {

    @PostMapping("/new")
    fun createComment(
        @ApiIgnore authentication: Authentication,
        @RequestBody @Valid request: CreateCommentRequest
    ): ResponseEntity<CommentResponse> =
        ResponseEntity.ok().body(
            commentFacade.create(
                authentication.name,
                request
            )
        )

    @GetMapping("/{postId}")
    fun getComments(
        @PathVariable("postId") postId: String
    ): ResponseEntity<List<CommentResponse>> =
        ResponseEntity.ok().body(
            commentFacade.getByPostId(postId)
        )

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @ApiIgnore authentication: Authentication,
        @PathVariable("commentId") commentId: String
    ): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            commentFacade.delete(
                authentication.name,
                commentId
            )
        )
}