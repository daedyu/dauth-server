package com.b1nd.dauthserver.infrastructure.web.data

data class BasicApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T
)
