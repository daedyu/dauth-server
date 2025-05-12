package com.b1nd.dauthserver.infrastructure.client.token.data

data class TokenValidateRequest(
    val token: String,
    val key: String
)
