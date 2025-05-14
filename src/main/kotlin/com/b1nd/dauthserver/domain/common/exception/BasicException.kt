package com.b1nd.dauthserver.domain.common.exception

import com.b1nd.dauthserver.domain.common.enumeration.StatusCode

open class BasicException(
    val statusCode: StatusCode
): RuntimeException()