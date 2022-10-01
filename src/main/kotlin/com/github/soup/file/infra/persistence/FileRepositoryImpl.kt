package com.github.soup.file.infra.persistence

import com.github.soup.file.domain.File
import com.github.soup.file.domain.FileRepository
import org.springframework.stereotype.Repository

@Repository
class FileRepositoryImpl(
    private val fileJpaRepository: FileJpaRepository
) : FileRepository {

    override fun save(file: File): File {
        return fileJpaRepository.save(file)
    }

    override fun delete(fileId: String) {
        fileJpaRepository.deleteById(fileId)
    }
}