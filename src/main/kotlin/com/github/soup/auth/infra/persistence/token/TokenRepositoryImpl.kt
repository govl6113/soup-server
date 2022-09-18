package com.github.soup.auth.infra.persistence.token

import com.github.soup.auth.domain.token.Token
import com.github.soup.auth.domain.token.TokenRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class TokenRepositoryImpl(
	private val redisTemplate: RedisTemplate<Any, Any>
) : TokenRepository {
	override fun getByKey(key: String): Token? {
		return getOperations().get(key)
	}

	override fun save(key: String, token: Token, timeToLive: Long): Token {
		getOperations().set(key, token, Duration.ofMillis(timeToLive))
		return token
	}

	override fun delete(key: String) {
		getOperations().getAndDelete(key)
	}

	private fun getOperations(): ValueOperations<String, Token> {
		redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(Token::class.java)
		return redisTemplate.opsForValue() as ValueOperations<String, Token>
	}
}