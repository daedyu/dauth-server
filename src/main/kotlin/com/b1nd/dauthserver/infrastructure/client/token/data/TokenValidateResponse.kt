package com.b1nd.dauthserver.infrastructure.client.token.data

data class TokenValidateResponse(
    val memberId: String,
    val accessLevel: Int,
    val apiKeyAccessLevel: Int
)