package com.b1nd.dauthserver.domain.auth.exception

import com.b1nd.dauthserver.domain.common.enumeration.StatusCode

enum class AuthenticationWordStatusCode(
    override val message: String,
    override val status: Int
): StatusCode {
    AUTHENTICATION_WORD_NOT_FOUND("Authentication Word Not Found", 404),
}