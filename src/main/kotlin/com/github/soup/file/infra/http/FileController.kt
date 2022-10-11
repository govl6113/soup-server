package com.github.soup.file.infra.http

import com.github.soup.file.application.facade.FileFacadeImpl
import com.github.soup.file.domain.FileType
import com.github.soup.file.infra.http.response.FileResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import springfox.documentation.annotations.ApiIgnore

@RestController
@RequestMapping("/api/file")
class FileController(
    private val fileFacade: FileFacadeImpl
) {

    @PostMapping("/upload")
    fun upload(@ApiIgnore authentication: Authentication, image: MultipartFile): ResponseEntity<FileResponse> =
        ResponseEntity.ok().body(fileFacade.upload(authentication.name, FileType.POST, image).toResponse())

    @PostMapping("/uploads")
    fun uploads(
        @ApiIgnore authentication: Authentication,
        images: List<MultipartFile>
    ): ResponseEntity<List<FileResponse>> =
        ResponseEntity.ok().body(
            fileFacade.uploads(authentication.name, FileType.POST, images).map { file -> file.toResponse() }
        )
}
