package com.github.soup.review.infra.persistence

import com.github.soup.review.domain.Review
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewJpaRepository : JpaRepository<Review, String> {
    fun findByToId(toId: String, pageable: Pageable): List<Review>

    fun findByFromIdAndToIdAndGroupId(fromId: String, toId: String, groupId: String): Review?
}