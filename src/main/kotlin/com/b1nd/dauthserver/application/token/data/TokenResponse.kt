package com.b1nd.dauthserver.application.token.data

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String,
    val tokenType: String = "Bearer"
)
