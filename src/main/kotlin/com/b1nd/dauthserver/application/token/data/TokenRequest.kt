package com.b1nd.dauthserver.application.token.data

import java.util.UUID

data class TokenRequest(
    val code: UUID,
    val clientSecret: String,
)