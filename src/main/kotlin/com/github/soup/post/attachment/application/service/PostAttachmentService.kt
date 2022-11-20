package com.github.soup.post.attachment.application.service

import com.github.soup.post.attachment.domain.PostAttachment

interface PostAttachmentService {

    fun save(postAttachment: PostAttachment): PostAttachment

}