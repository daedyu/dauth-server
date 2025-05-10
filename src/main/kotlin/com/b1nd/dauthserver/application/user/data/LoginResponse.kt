package com.b1nd.dauthserver.application.user.data

import com.b1nd.dauthserver.domain.user.entity.UserEntity

data class LoginResponse(
    val code: String,
    val redirectUrl: String
) {
    companion object {
        fun of(code: String, redirectUrl: String) =
            LoginResponse(code, redirectUrl)
    }
}