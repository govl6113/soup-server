package com.github.soup.file.application.facade

import com.github.soup.file.domain.File
import com.github.soup.file.domain.FileType
import org.springframework.web.multipart.MultipartFile

interface FileFacade {
    fun upload(memberId: String, type: FileType, image: MultipartFile): File

    fun uploads(memberId: String, type: FileType, images: List<MultipartFile>): List<File>
}