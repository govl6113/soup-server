package com.github.soup.file.application.facade

import com.github.soup.file.domain.FileType
import com.github.soup.file.infra.http.FileResponse
import org.springframework.web.multipart.MultipartFile

interface FileFacade {
    fun upload(memberId: String, type: FileType, image: MultipartFile): FileResponse

    fun uploads(memberId: String, type: FileType, images: List<MultipartFile>): List<FileResponse>
}