package com.github.soup.review.infra.http.request

import javax.validation.constraints.NotBlank

data class CreateReviewRequest(
    @NotBlank
    var targetId: String,

    @NotBlank
    var content: String,

    var score: Float,
)