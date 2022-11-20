package com.github.soup.review.infra.http

import com.github.soup.review.application.facade.ReviewFacadeImpl
import com.github.soup.review.infra.http.request.CreateReviewRequest
import com.github.soup.review.infra.http.request.UpdateReviewRequest
import com.github.soup.review.infra.http.response.ReviewResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

@RestController
@RequestMapping("/api/review")
class ReviewController(
    private val reviewFacade: ReviewFacadeImpl
) {

    @PostMapping("/new")
    fun createReview(
        @ApiIgnore authentication: Authentication,
        @RequestBody @Valid request: CreateReviewRequest
    ): ResponseEntity<ReviewResponse> =
        ResponseEntity.ok().body(
            reviewFacade.create(
                authentication.name,
                request
            )
        )

    @PatchMapping("/{reviewId}")
    fun updateReview(
        @ApiIgnore authentication: Authentication,
        @PathVariable("reviewId") reviewId: String,
        @RequestBody @Valid request: UpdateReviewRequest
    ): ResponseEntity<ReviewResponse> =
        ResponseEntity.ok().body(
            reviewFacade.update(
                authentication.name,
                reviewId,
                request
            )
        )

    @GetMapping("/list/{toId}")
    fun getList(
        @ApiIgnore authentication: Authentication,
        @PathVariable("toId") toId: String,
        @RequestParam(value = "page", required = false, defaultValue = "1") page: Int,
    ): ResponseEntity<List<ReviewResponse>> =
        ResponseEntity.ok().body(
            reviewFacade.getList(
                authentication.name,
                toId,
                page
            )
        )

    @GetMapping("/{reviewId}")
    fun get(
        @ApiIgnore authentication: Authentication,
        @PathVariable("reviewId") reviewId: String
    ): ResponseEntity<ReviewResponse> =
        ResponseEntity.ok().body(
            reviewFacade.get(
                authentication.name,
                reviewId
            )
        )

    @DeleteMapping("/{reviewId}")
    fun deleteScrap(
        @ApiIgnore authentication: Authentication,
        @PathVariable("reviewId") reviewId: String
    ): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            reviewFacade.delete(
                authentication.name,
                reviewId
            )
        )
}