package com.b1nd.dauthserver.application.token.data

import jakarta.validation.constraints.NotBlank

data class TokenRefreshRequest(
    @field:NotBlank
    val refresh: String,
    @field:NotBlank
    val clientId: String
)