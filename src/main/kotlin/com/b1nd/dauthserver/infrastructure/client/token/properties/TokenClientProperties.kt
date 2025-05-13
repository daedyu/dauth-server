package com.b1nd.dauthserver.infrastructure.client.token.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.token")
data class TokenClientProperties(
    val tokenEndpoint: String,
    val access: String,
    val reissue: String,
    val refresh: String,
    val verify: String,
    val key: String
)