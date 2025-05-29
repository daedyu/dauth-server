package com.b1nd.dauthserver.application.auth.data

data class QrLoginResponse(
    val code: String,
    val words: String,
    val word: String
)