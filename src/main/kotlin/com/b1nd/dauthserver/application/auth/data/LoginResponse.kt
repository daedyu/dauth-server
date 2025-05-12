package com.b1nd.dauthserver.application.auth.data

data class LoginResponse(
    val code: String,
    val redirectUrl: String
) {
    companion object {
        fun of(code: String, redirectUrl: String) =
            LoginResponse(code, redirectUrl)
    }
}