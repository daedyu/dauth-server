package com.b1nd.dauthserver.infrastructure.database.redis.service

import com.b1nd.dauthserver.infrastructure.database.redis.enumeration.RedisKeyType
import com.b1nd.dauthserver.infrastructure.database.redis.exception.RedisKeyNotFoundException
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisService(
    private val redisTemplate: ReactiveRedisTemplate<String, String>
) {
    suspend fun save(type: RedisKeyType, key: String, value: String): String {
        redisTemplate.opsForValue().set(joinTypeAndKey(type, key), value, Duration.ofHours(1)).awaitSingle()
        return key
    }

    suspend fun update(type: RedisKeyType, key: String, value: String) =
        redisTemplate.opsForValue().getAndSet(joinTypeAndKey(type, key), value).awaitSingleOrNull()
            ?: throw RedisKeyNotFoundException()


    suspend fun exist(type: RedisKeyType, key: String): Boolean =
        redisTemplate.hasKey(joinTypeAndKey(type, key)).awaitSingle()

    suspend fun get(type: RedisKeyType, key: String): String =
        redisTemplate.opsForValue().get(joinTypeAndKey(type, key)).awaitSingleOrNull()?: throw RedisKeyNotFoundException()

    suspend fun delete(type: RedisKeyType, key: String) =
        redisTemplate.delete(joinTypeAndKey(type, key)).awaitSingle()

    private fun joinTypeAndKey(type: RedisKeyType, key: String): String =
        type.name + "::" + key
}