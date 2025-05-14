package com.b1nd.dauthserver.infrastructure.database.redis.exception

import com.b1nd.dauthserver.domain.common.enumeration.StatusCode

enum class RedisExceptionStatusCode(
    override val message: String,
    override val status: Int
): StatusCode {
    KEY_NOT_FOUND("찾을 수 없는 키", 404),
}