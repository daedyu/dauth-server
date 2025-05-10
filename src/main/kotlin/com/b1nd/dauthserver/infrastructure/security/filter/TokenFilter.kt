package com.b1nd.dauthserver.infrastructure.security.filter

import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class TokenFilter: WebFilter {
    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain
    ): Mono<Void> {
        TODO("Not yet implemented")
    }
}
