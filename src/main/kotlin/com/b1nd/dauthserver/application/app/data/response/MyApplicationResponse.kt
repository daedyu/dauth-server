package com.b1nd.dauthserver.application.app.data.response

data class MyApplicationResponse(
    val user: Long,
    val applications: List<ApplicationResponse>
)