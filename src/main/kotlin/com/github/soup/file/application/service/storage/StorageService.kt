package com.github.soup.file.application.service.storage

import org.springframework.web.multipart.MultipartFile

interface StorageService {
    fun upload(key: String, image: MultipartFile): Boolean

    fun delete(key: String): Boolean
}