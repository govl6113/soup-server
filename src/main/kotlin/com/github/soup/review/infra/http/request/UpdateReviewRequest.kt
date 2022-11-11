package com.github.soup.review.infra.http.request

import javax.validation.constraints.NotBlank

data class UpdateReviewRequest(

    @NotBlank
    val content: String,

    val score: Float
)