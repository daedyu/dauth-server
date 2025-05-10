package com.b1nd.dauthserver.infrastructure.web.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    fun webClient(): WebClient = WebClient.create()
}
