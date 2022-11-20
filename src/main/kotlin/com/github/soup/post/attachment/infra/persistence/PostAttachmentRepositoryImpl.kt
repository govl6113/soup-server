package com.github.soup.post.attachment.infra.persistence

import com.github.soup.post.attachment.domain.PostAttachment
import org.springframework.stereotype.Repository

@Repository
class PostAttachmentRepositoryImpl(
    private val postAttachmentRepository: PostAttachmentJpaRepository
) : PostAttachmentRepository {

    override fun save(postAttachment: PostAttachment): PostAttachment {
        return postAttachmentRepository.save(postAttachment)
    }

}