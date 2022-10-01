package com.github.soup.file.application.facade

import com.github.soup.file.application.service.file.FileServiceImpl
import com.github.soup.file.application.service.storage.StorageServiceImpl
import com.github.soup.file.domain.File
import com.github.soup.file.domain.FileType
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

    override fun upload(memberId: String, type: FileType, image: MultipartFile): File {
        val member: Member = memberService.getByMemberId(memberId)

        val file: File = fileService.save(
            uploader = member,
            type = type,
            image = image
        )

        storageService.upload(
            key = file.key,
            image = image
        )
        return file
    }

    override fun uploads(memberId: String, type: FileType, images: List<MultipartFile>): List<File> {
        val member: Member = memberService.getByMemberId(memberId)

        return images.map { image ->
            val file: File = fileService.save(member, type, image)
            storageService.upload(key = file.key, image = image)
            file
        }
    }

}