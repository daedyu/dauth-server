package com.b1nd.dauthserver.infrastructure.client.dodam.exception

import com.b1nd.dauthserver.domain.common.enumeration.GlobalExceptionStatusCode
import com.b1nd.dauthserver.domain.common.exception.BasicException

class DodamClientException (code: Int) : BasicException(
     statusCode = when (code) {
        400 -> GlobalExceptionStatusCode.TOKEN_NOT_PROVIDED
        401,500 -> GlobalExceptionStatusCode.INVALID_TOKEN
        403 -> GlobalExceptionStatusCode.INVALID_PASSWORD
        404 -> GlobalExceptionStatusCode.USER_NOT_FOUND
        else -> GlobalExceptionStatusCode.INTERNAL_SERVER_ERROR
     }
)