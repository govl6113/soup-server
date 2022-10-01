package com.github.soup.file.application.service.file

import com.github.soup.file.domain.File
import com.github.soup.file.domain.FileType
import com.github.soup.member.domain.Member
import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun save(uploader: Member, type: FileType, image: MultipartFile): File

    fun delete(fileId: String)
}