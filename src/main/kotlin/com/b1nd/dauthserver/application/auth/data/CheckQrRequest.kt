package com.b1nd.dauthserver.application.auth.data

data class CheckQrRequest(
    val code: String,
    val redirectUrl: String
)
