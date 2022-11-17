package com.github.soup.review.application.service

import com.github.soup.review.domain.Review

interface ReviewService {
    fun save(review: Review): Review

    fun getById(reviewId: String): Review

    fun getByToId(toId: String, page: Int): List<Review>

    fun delete(review: Review)
}