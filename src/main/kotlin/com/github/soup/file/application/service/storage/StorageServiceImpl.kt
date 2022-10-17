package com.github.soup.file.application.service.storage

import com.github.soup.config.logger
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import io.minio.errors.MinioException
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
class StorageServiceImpl(
	private val minioClient: MinioClient,

	@Value("\${storage.bucket}")
	private val bucketName: String
) : StorageService {

	private final val log: Logger = logger<StorageServiceImpl>()

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
		} catch (exception: MinioException) {
			log.error("[StorageServiceImpl.upload Error] ${exception.message}")
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
		} catch (exception: MinioException) {
			log.error("[StorageServiceImpl.delete Error] ${exception.message}")
			false
		}
	}
}
