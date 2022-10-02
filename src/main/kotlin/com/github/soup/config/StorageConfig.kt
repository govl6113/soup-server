package com.github.soup.config

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class StorageConfig {
    @Value("\${storage.endpoint}")
    private val endpoint: String? = null

    @Value("\${storage.accessKey}")
    private val accessKey: String? = null

    @Value("\${storage.secretKey}")
    private val secretKey: String? = null

    @Bean
    @Primary
    fun minioClient(): MinioClient {
        return MinioClient.Builder()
            .credentials(accessKey, secretKey)
            .endpoint(endpoint)
            .build()
    }
}