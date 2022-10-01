package com.github.soup.file.domain

import com.github.soup.common.domain.Core
import com.github.soup.file.infra.http.FileResponse
import com.github.soup.member.domain.Member
import org.springframework.beans.factory.annotation.Value
import java.util.*
import javax.persistence.*


@Entity
class File(

    @ManyToOne(targetEntity = Member::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    var uploader: Member,

    @Column(name = "file_key", nullable = false)
    var key: String,

    @Enumerated(EnumType.STRING)
    var type: FileType

) : Core() {

    @Value("\${storage.host}")
    private val host: String = ""

    constructor(uploader: Member, type: FileType, mime: String) : this(
        uploader = uploader,
        key = "$type/${UUID.randomUUID()}.$mime",
        type = type
    )

    fun toResponse(): FileResponse {
        return FileResponse(
            id!!,
            createdAt!!,
            updatedAt!!,
            "$host/$type/$key"
        )
    }
}