package com.b1nd.dauthserver.application.token.data

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenResponse(
    @field:JsonProperty("access_token")
    val accessToken: String,
    @field:JsonProperty("refresh_token")
    val refreshToken: String,
    @field:JsonProperty("id_token")
    val idToken: String,
    @field:JsonProperty("token_type")
    val tokenType: String = "Bearer"
)
