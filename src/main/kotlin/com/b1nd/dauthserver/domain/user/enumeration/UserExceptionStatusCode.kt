package com.b1nd.dauthserver.domain.user.enumeration

import com.b1nd.dauthserver.domain.common.enumeration.StatusCode

enum class UserExceptionStatusCode(
    override val message: String,
    override val status: Int
): StatusCode {
    USER_NOT_FOUND("User Not Found", 404),
}