package com.b1nd.dauthserver.domain.user.enumeration

import com.b1nd.dauthserver.domain.common.enumeration.StatusCode

enum class UserExceptionStatusCode(
    override val message: String,
    override val status: Int
): StatusCode {
    USER_NOT_FOUND("User Not Found", 404),
    CODE_NOT_FOUND("Code Not Found", 404),
    CODE_NOT_APPLIED("인증되지 않은 코드", 403)
}