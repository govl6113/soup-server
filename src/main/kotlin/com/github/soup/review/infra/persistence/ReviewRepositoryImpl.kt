package com.github.soup.review.infra.persistence

import com.github.soup.review.domain.Review
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class ReviewRepositoryImpl(
    private val reviewRepository: ReviewJpaRepository,
) : ReviewRepository {

    override fun save(review: Review): Review {
        return reviewRepository.save(review)
    }

    override fun getById(reviewId: String): Optional<Review> {
        return reviewRepository.findById(reviewId)
    }

    override fun getByToId(toId: String, pageable: Pageable): List<Review> {
        return reviewRepository.findByToId(toId, pageable)
    }

    override fun delete(review: Review) {
        reviewRepository.delete(review)
    }
}
