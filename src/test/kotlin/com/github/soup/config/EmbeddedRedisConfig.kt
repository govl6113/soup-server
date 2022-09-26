package com.github.soup.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import redis.embedded.RedisServer
import redis.embedded.RedisServerBuilder
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@TestConfiguration
class EmbeddedRedisConfig(
	@Value("\${spring.redis.port}") private val port: Int
) {
	private val redisServer: RedisServer = RedisServerBuilder().port(port).build()

	@PostConstruct
	fun postConstruct() {
		redisServer.start()
	}

	@PreDestroy
	fun preDestroy() {
		redisServer.stop()
	}
}