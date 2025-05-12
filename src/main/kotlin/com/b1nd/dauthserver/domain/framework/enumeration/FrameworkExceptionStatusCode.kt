package com.b1nd.dauthserver.domain.framework.enumeration

import com.b1nd.dauthserver.domain.common.enumeration.StatusCode

enum class FrameworkExceptionStatusCode(
    override val message: String,
    override val status: Int
): StatusCode {
    FRAMEWORK_NOT_FOUND("Framework Not Found", 404),
}