package com.github.soup.auth.application.config

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import redis.embedded.RedisServer

@TestConfiguration
class EmbeddedRedisConfig : InitializingBean, DisposableBean {
	@Value("\${spring.redis.port}")
	private lateinit var port: String

	private lateinit var redisService: RedisServer

	override fun afterPropertiesSet() {
		this.redisService = RedisServer(this.port.toInt())
		this.redisService.start()
	}

	override fun destroy() {
		this.redisService.stop()
	}
}