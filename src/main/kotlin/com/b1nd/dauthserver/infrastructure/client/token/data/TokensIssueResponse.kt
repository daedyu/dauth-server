package com.b1nd.dauthserver.infrastructure.client.token.data

data class TokensIssueResponse(
    val access: String
    val refresh: String
)