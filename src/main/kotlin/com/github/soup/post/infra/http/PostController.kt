package com.github.soup.post.infra.http

import com.github.soup.post.appllication.facade.PostFacadeImpl
import com.github.soup.post.infra.http.request.CreatePostRequest
import com.github.soup.post.infra.http.request.UpdatePostRequest
import com.github.soup.post.infra.http.response.PostResponse
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

@RestController
@RequestMapping("/api/post")
class PostController(
    private val postFacade: PostFacadeImpl
) {
    @ApiOperation(value = "게시글 생성")
    @PostMapping("/new")
    fun createPost(
        @ApiIgnore authentication: Authentication,
        @Valid request: CreatePostRequest
    ): ResponseEntity<PostResponse> =
        ResponseEntity.ok().body(
            postFacade.create(
                authentication.name,
                request
            )
        )

    @ApiOperation(value = "게시글 조회")
    @GetMapping("/{postId}")
    fun getGroup(
        @ApiIgnore authentication: Authentication,
        @PathVariable("postId") postId: String,
    ): ResponseEntity<PostResponse> =
        ResponseEntity.ok().body(
            postFacade.get(
                authentication.name,
                postId
            )
        )

    @ApiOperation(value = "게시글 수정")
    @PatchMapping("/{postId}")
    fun updatePost(
        @ApiIgnore authentication: Authentication,
        @PathVariable("postId") postId: String,
        @Valid request: UpdatePostRequest
    ): ResponseEntity<PostResponse> =
        ResponseEntity.ok().body(
            postFacade.update(
                authentication.name,
                postId,
                request
            )
        )

    @ApiOperation(value = "게시글 삭제")
    @GetMapping("/{postId}")
    fun deletePost(
        @ApiIgnore authentication: Authentication,
        @PathVariable("postId") postId: String,
    ): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            postFacade.delete(
                authentication.name,
                postId
            )
        )
}