package com.github.soup.member.infra.http.response

import com.github.soup.file.infra.http.response.FileResponse
import com.github.soup.member.domain.SexType
import java.time.LocalDateTime

class MemberResponse(
    val id: String,
    val name: String,
    val nickName: String,
    val sex: SexType,
    val bio: String?,
    val image: FileResponse?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
