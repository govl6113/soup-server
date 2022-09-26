package com.github.soup.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
	@Value("\${spring.redis.host}")
	private lateinit var host: String

	@Value("\${spring.redis.port}")
	private lateinit var port: String

	@Value("\${spring.redis.password}")
	private lateinit var password: String

	@Bean
	fun redisConnectionFactory(): RedisConnectionFactory {
		val configuration = RedisStandaloneConfiguration()
		configuration.hostName = host
		configuration.port = port.toInt()
		configuration.password = RedisPassword.of(password)
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