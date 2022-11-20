package com.github.soup.post.attachment.infra.persistence

import com.github.soup.post.attachment.domain.PostAttachment
import org.springframework.data.jpa.repository.JpaRepository

interface PostAttachmentJpaRepository : JpaRepository<PostAttachment, String>