package com.b1nd.dauthserver.infrastructure.security.token.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.token")
data class TokenProperties(
    val expire: Long
)
