package com.github.soup.review.application.facade

import com.github.soup.review.infra.http.request.CreateReviewRequest
import com.github.soup.review.infra.http.request.UpdateReviewRequest
import com.github.soup.review.infra.http.response.ReviewResponse


interface ReviewFacade {

    fun create(memberId: String, request: CreateReviewRequest): ReviewResponse

    fun update(memberId: String, reviewId: String, request: UpdateReviewRequest): ReviewResponse

    fun getList(fromId: String, toId: String, page: Int): List<ReviewResponse>

    fun get(memberId: String, reviewId: String): ReviewResponse

    fun delete(memberId: String, reviewId: String): Boolean
}