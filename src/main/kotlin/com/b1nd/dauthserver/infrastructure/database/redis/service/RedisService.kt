package com.b1nd.dauthserver.infrastructure.database.redis.service

import com.b1nd.dauthserver.infrastructure.database.redis.exception.RedisKeyNotFoundException
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisService(
    private val redisTemplate: ReactiveRedisTemplate<String, Long>
) {
    suspend fun save(key: String, value: Long): String {
        redisTemplate.opsForValue().set(key, value, Duration.ofHours(1)).awaitSingle()
        return key
    }

    suspend fun get(key: String): Long =
        redisTemplate.opsForValue().get(key).awaitSingle()?: throw RedisKeyNotFoundException()

    suspend fun delete(key: String) =
        redisTemplate.delete(key).awaitSingle()
}