package com.github.soup.file.domain

import com.github.soup.common.domain.Core
import com.github.soup.file.infra.http.response.FileResponse
import com.github.soup.member.domain.Member
import java.util.*
import javax.persistence.*


@Entity
class File(

    @ManyToOne(targetEntity = Member::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    var uploader: Member,

    @Column(name = "file_key", nullable = false)
    var key: String,

    ) : Core() {

    val host: String = "133.186.146.107:9001"

    constructor(uploader: Member, type: FileType, mime: String) : this(
        uploader = uploader,
        key = "$type/${UUID.randomUUID()}.$mime",
    )

    fun toResponse(): FileResponse {
        return FileResponse(
            id!!,
            createdAt!!,
            updatedAt!!,
            "$host/$key"
        )
    }
}