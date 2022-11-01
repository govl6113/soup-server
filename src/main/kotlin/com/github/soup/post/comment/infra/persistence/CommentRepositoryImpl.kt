package com.github.soup.post.comment.infra.persistence

import com.github.soup.post.comment.domain.Comment
import com.github.soup.post.comment.domain.QComment.comment
import com.github.soup.post.domain.Post
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CommentRepositoryImpl(
    private val commentRepository: CommentJpaRepository,
    private val queryFactory: JPAQueryFactory,
) : CommentRepository {

    override fun save(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    override fun getById(commentId: String): Optional<Comment> {
        return commentRepository.findById(commentId)
    }

    override fun getByPost(post: Post): List<Comment> {
        return queryFactory
            .selectFrom(comment)
            .where(
                comment.post.eq(post),
                comment.parent.isNull
            )
            .orderBy(comment.updatedAt.asc())
            .fetch()
    }

    override fun delete(comment: Comment) {
        return commentRepository.delete(comment)
    }

    override fun deleteAllInBatch(comments: List<Comment>) {
        return commentRepository.deleteAllInBatch(comments)
    }

}