package com.github.soup.review.infra.persistence

import com.github.soup.review.domain.Review
import org.springframework.data.domain.Pageable
import java.util.*

interface ReviewRepository {
    fun save(review: Review): Review

    fun getById(reviewId: String): Optional<Review>

    fun getByToId(toId: String, pageable: Pageable): List<Review>

    fun delete(review: Review)
}