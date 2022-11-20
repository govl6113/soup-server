package com.github.soup.post.attachment.infra.persistence

import com.github.soup.post.attachment.domain.PostAttachment

interface PostAttachmentRepository {

    fun save(postAttachment: PostAttachment): PostAttachment

}