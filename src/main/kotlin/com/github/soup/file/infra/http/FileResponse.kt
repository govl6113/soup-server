package com.github.soup.file.infra.http

import java.time.LocalDateTime

data class FileResponse(
    val id: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val url: String
)