package com.github.soup.file.application.facade

import com.github.soup.file.application.service.file.FileServiceImpl
import com.github.soup.file.application.service.storage.StorageServiceImpl
import com.github.soup.file.domain.File
import com.github.soup.file.domain.FileType
import com.github.soup.file.infra.http.FileResponse
import com.github.soup.member.application.service.MemberServiceImpl
import com.github.soup.member.domain.Member
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileFacadeImpl(
    private val fileService: FileServiceImpl,
    private val storageService: StorageServiceImpl,
    private val memberService: MemberServiceImpl
) : FileFacade {

    override fun upload(memberId: String, type: FileType, image: MultipartFile): FileResponse {
        val member: Member = memberService.getByMemberId(memberId)

        val file: File = fileService.upload(
            uploader = member,
            type = type,
            image = image
        )

        storageService.upload(
            key = file.key,
            image = image
        )
        return file.toResponse()
    }

    override fun uploads(memberId: String, type: FileType, images: List<MultipartFile>): List<FileResponse> {
        val member: Member = memberService.getByMemberId(memberId)

        return images.map { image ->
            val file: File = fileService.upload(member, type, image)
            storageService.upload(key = file.key, image = image)
            file.toResponse()
        }
    }

}