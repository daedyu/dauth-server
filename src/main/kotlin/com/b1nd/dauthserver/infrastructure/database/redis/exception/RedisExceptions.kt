package com.b1nd.dauthserver.infrastructure.database.redis.exception

import com.b1nd.dauthserver.domain.common.exception.BasicException

class RedisKeyNotFoundException : BasicException(RedisExceptionStatusCode.KEY_NOT_FOUND)