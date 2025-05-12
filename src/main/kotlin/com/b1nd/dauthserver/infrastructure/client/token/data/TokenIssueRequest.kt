package com.b1nd.dauthserver.infrastructure.client.token.data

data class TokenIssueRequest(
    val memberId: String,
    val accessLevel: Int,
    val key: String
)