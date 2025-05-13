package com.b1nd.dauthserver.application.auth.data

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponse(
    val code: String,
    @field:JsonProperty("redirect_url")
    val redirectUrl: String
) {
    companion object {
        fun of(code: String, redirectUrl: String) =
            LoginResponse(code, redirectUrl)
    }
}