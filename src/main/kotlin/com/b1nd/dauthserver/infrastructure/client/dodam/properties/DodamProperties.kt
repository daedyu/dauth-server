package com.b1nd.dauthserver.infrastructure.client.dodam.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.dodam")
data class DodamProperties(
    val endpoint: String
)