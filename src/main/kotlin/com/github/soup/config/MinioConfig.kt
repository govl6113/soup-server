package com.github.soup.config

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class MinioConfig {
    @Value("\${minio.endpoint}")
    private val endpoint: String? = null

    @Value("\${minio.accessKey}")
    private val accessKey: String? = null

    @Value("\${minio.secretKey}")
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