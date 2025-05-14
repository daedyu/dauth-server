package com.b1nd.dauthserver.application.auth.data

import jakarta.validation.constraints.NotBlank

data class QrLoginRequest(
    @field:NotBlank
    val code: String,
    @field:NotBlank
    val access: String,
    @field:NotBlank
    val refresh: String,
    @field:NotBlank
    val clientId: String,
)