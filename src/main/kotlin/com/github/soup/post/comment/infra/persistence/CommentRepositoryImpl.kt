package com.github.soup.post.comment.infra.persistence

import com.github.soup.post.comment.domain.Comment
import com.github.soup.post.domain.Post
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CommentRepositoryImpl(
    private val commentRepository: CommentJpaRepository
) : CommentRepository {

    override fun save(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    override fun getById(commentId: String): Optional<Comment> {
        return commentRepository.findById(commentId)
    }

    override fun getByPost(post: Post): List<Comment> {
        return commentRepository.findByPost(post)
    }

    override fun delete(comment: Comment) {
        return commentRepository.delete(comment)
    }

    override fun deleteAllInBatch(comments: List<Comment>) {
        return commentRepository.deleteAllInBatch(comments)
    }

}