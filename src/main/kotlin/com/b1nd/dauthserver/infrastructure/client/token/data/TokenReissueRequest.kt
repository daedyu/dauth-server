package com.b1nd.dauthserver.infrastructure.client.token.data

data class TokenReissueRequest(val refreshToken: String, val key: String)
