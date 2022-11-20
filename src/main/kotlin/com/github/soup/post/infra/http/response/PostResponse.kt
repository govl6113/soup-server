package com.github.soup.post.infra.http.response

import com.github.soup.file.infra.http.response.FileResponse
import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.member.infra.http.response.MemberResponse
import com.github.soup.post.domain.PostTypeEnum
import java.time.LocalDateTime

data class PostResponse(
    val id: String,
    val writer: MemberResponse,
    val group: GroupResponse,
    val type: PostTypeEnum,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val postAttachments: List<FileResponse>?
)