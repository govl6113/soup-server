package com.github.soup.file.application.service.storage

import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class StorageServiceImpl(
    private val minioClient: MinioClient
) : StorageService {

    @Value("\${storage.bucket}")
    private val bucketName: String = "bucket"

    override fun upload(key: String, image: MultipartFile): Boolean {
        return try {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`(key)
                    .stream(image.inputStream, image.size, -1)
                    .build()
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun delete(key: String): Boolean {
        return try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`(key)
                    .build()
            )
            true
        } catch (e: Exception) {
            false
        }
    }
}
