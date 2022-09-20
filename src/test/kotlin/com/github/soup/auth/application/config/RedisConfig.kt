package com.github.soup.auth.application.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@TestConfiguration
class RedisConfig {
	@Value("\${spring.redis.host}")
	private lateinit var host: String

	@Value("\${spring.redis.port}")
	private lateinit var port: String

	@Bean
	fun redisConnectionFactory(): RedisConnectionFactory {
		val configuration = RedisStandaloneConfiguration()
		configuration.hostName = host
		configuration.port = port.toInt()
		return LettuceConnectionFactory(configuration)
	}

	@Bean
	fun redisTemplate(): RedisTemplate<Any, Any> {
		val redisTemplate = RedisTemplate<Any, Any>()
		redisTemplate.setConnectionFactory(redisConnectionFactory())
		redisTemplate.keySerializer = StringRedisSerializer()
		return redisTemplate
	}
}